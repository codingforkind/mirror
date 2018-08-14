package cn.com.mirror.reflect;

import cn.com.mirror.analyser.PairAnalyser;
import cn.com.mirror.analyser.UnitAnalyser;
import cn.com.mirror.project.pair.Pair;
import cn.com.mirror.project.unit.Unit;
import lombok.Data;

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

    }
}
