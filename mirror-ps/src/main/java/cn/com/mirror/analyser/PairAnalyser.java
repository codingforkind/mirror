package cn.com.mirror.analyser;

import cn.com.mirror.project.config.ProjectProperty;
import cn.com.mirror.project.pair.Pair;
import cn.com.mirror.project.pair.factory.PairFactory;
import cn.com.mirror.project.code.CodeLoader;
import cn.com.mirror.project.code.LocalLoader;

/**
 * @author piggy
 * @description
 * @date 18-8-14
 */
public class PairAnalyser {

    public Pair analyze(ProjectProperty projectProperty) {
        PairFactory pairFactory = new PairFactory();

        Pair pair = null;
        if (null == projectProperty) {
            pair = pairFactory.newPair(LocalLoader.getPrjProperty());
        } else {
            pair = pairFactory.newPair(projectProperty);
        }
        return pair;
    }

}
