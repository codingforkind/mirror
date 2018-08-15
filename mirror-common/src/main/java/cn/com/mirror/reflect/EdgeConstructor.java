package cn.com.mirror.reflect;

import cn.com.mirror.analyser.PairAnalyser;
import cn.com.mirror.analyser.UnitAnalyser;
import cn.com.mirror.project.pair.Pair;
import cn.com.mirror.project.unit.Unit;
import cn.com.mirror.project.unit.element.Class;
import cn.com.mirror.project.unit.element.Variable;
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

        Map<String, Map<Integer, Set<Variable>>> mappedVars = unit.getMappedVars();
        Map<String, Set<Class>> mappedClasses = unit.getClasses();
        entrySet.stream().forEach(entry -> {
            String targetPath = entry.getKey();
            // all element in the unit
            Map<Integer, Set<Variable>> varsInTarget = mappedVars.get(targetPath);

            // basic control edges in the target file
            Map<Integer, Integer> directCtrlEdgeMap = entry.getValue();
            directCtrlEdgeMap.forEach((ctrlKey, ctrlVal) -> {
                // create ctrlKey node and ctrlVal node as a statement and build up they relationships
                //TODO xyz create node and build relationships
                Set<Variable> varsInHead = varsInTarget.get(ctrlKey);
                Set<Variable> varsInTail = varsInTarget.get(ctrlVal);

            });


            Map<Integer, Set<Variable>> integerSetMap = mappedVars.get(targetPath);

        });

    }
}
