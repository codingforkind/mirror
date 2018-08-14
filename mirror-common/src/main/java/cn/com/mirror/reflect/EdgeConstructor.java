package cn.com.mirror.reflect;

import cn.com.mirror.analyser.PairAnalyser;
import cn.com.mirror.analyser.UnitAnalyser;
import cn.com.mirror.project.pair.Pair;
import cn.com.mirror.project.unit.Unit;
import cn.com.mirror.project.unit.variable.Variable;
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

        entrySet.stream().forEach(entry -> {
            String targetPath = entry.getKey();
            // all variable in the unit
            Map<String, Map<Integer, Set<Variable>>> mappedVars = unit.getMappedVars();

            Map<Integer, Integer> directCtrlEdgeMap = entry.getValue();


            Map<Integer, Set<Variable>> integerSetMap = mappedVars.get(targetPath);

        });

    }
}
