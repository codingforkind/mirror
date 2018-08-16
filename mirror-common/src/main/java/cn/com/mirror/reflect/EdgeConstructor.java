package cn.com.mirror.reflect;

import cn.com.mirror.analyser.PairAnalyser;
import cn.com.mirror.analyser.UnitAnalyser;
import cn.com.mirror.project.pair.Pair;
import cn.com.mirror.project.unit.Unit;
import cn.com.mirror.project.unit.element.Class;
import cn.com.mirror.project.unit.element.Method;
import cn.com.mirror.project.unit.element.Statement;
import cn.com.mirror.repository.neo4j.node.StatementNode;
import lombok.Data;

import java.util.Map;
import java.util.Set;

/**
 * @author piggy
 * @description
 * @date 18-8-10
 */
@Data
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
        Set<Map.Entry<String, Map<Integer, Integer>>> entrySet =
                pair.getDirectCtrlEdges().entrySet();

        Map<String, Set<Class>> mappedClasses = unit.getClasses();
        Map<String, Set<Method>> mappedMethods = unit.getMethods();
        Map<String, Map<Integer, Statement>> mappedStatements = unit.getStatements();

        entrySet.stream().forEach(entry -> {
            String targetPath = entry.getKey();
            // all element in the unit
            Set<Class> classesInTarget = mappedClasses.get(targetPath);
            Set<Method> methodsInTarget = mappedMethods.get(targetPath);
            Map<Integer, Statement> varsInTarget = mappedStatements.get(targetPath);


            // basic control edges in the target file
            Map<Integer, Integer> directCtrlEdgeMap = entry.getValue();
            directCtrlEdgeMap.forEach((ctrlKey, ctrlVal) -> {
                // create ctrlKey node and ctrlVal node as a statements and build up they relationships
                //TODO xyz create node and build relationships
                Statement headStat = varsInTarget.get(ctrlKey);



                Statement tailStat = varsInTarget.get(ctrlVal);


            });


            Map<Integer, Statement> integerSetMap = mappedStatements.get(targetPath);

        });

    }
}
