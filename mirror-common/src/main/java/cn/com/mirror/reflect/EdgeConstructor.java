package cn.com.mirror.reflect;

import cn.com.mirror.analyser.PairAnalyser;
import cn.com.mirror.analyser.UnitAnalyser;
import cn.com.mirror.project.pair.Pair;
import cn.com.mirror.project.unit.Unit;
import cn.com.mirror.project.unit.element.Phony;
import cn.com.mirror.project.unit.element.Statement;
import cn.com.mirror.repository.neo4j.node.StatementNode;
import cn.com.mirror.repository.neo4j.storage.GraphEngine;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;

/**
 * @author piggy
 * @description
 * @date 18-8-10
 */
@Data
@Slf4j
public class EdgeConstructor {

    private Unit unit;
    private Pair pair;

    public void preConstruct() {
        // analyze the project
        PairAnalyser pairAnalyser = new PairAnalyser();
        UnitAnalyser unitAnalyser = new UnitAnalyser();

        unit = unitAnalyser.analyze();
        pair = pairAnalyser.analyze();
    }

    public void construct() {
        // construct
        Set<Map.Entry<String, Map<Integer, Integer>>> entrySet = pair.getDirectCtrlEdges().entrySet();
        Map<String, Map<Integer, Statement>> mappedStatements = unit.getStatements();

        GraphEngine graphEngine = new GraphEngine();
        entrySet.stream().forEach(entry -> {
            String targetPath = entry.getKey();
            log.debug("TARGET: {}", targetPath);
            // all element in the unit
            Map<Integer, Statement> varsInTarget = mappedStatements.get(targetPath);


            // basic control edges in the target file
            Map<Integer, Integer> directCtrlEdgeMap = entry.getValue();
            directCtrlEdgeMap.forEach((ctrlKey, ctrlVal) -> {

                // create ctrlKey node and ctrlVal node as a statements and build up they relationships
                Statement headStat = varsInTarget.get(ctrlKey);
                Statement tailStat = varsInTarget.get(ctrlVal);

                if (!(tailStat.getInMethod() instanceof Phony)) {
                    // field control dependence on type
                    log.debug("HEAD statement: {}", headStat);
                    StatementNode headNode = StatementNode.instance(headStat);

                    log.debug("TAIL statement: {}", tailStat);
                    StatementNode tailNode = StatementNode.instance(tailStat);
                    headNode.setCtrlDepNode(tailNode);
                    graphEngine.write(headNode);
                    return;
                } else {
                    log.warn("HEAD: {} -> TAIL: {}", ctrlKey, ctrlVal);
                }

            });

        });

    }
}
