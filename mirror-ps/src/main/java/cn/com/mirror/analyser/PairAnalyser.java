package cn.com.mirror.analyser;

import cn.com.mirror.project.config.ProjectProperty;
import cn.com.mirror.project.pair.Pair;
import cn.com.mirror.project.pair.factory.PairFactory;

/**
 * @author piggy
 * @description
 * @date 18-8-14
 */
public class PairAnalyser {

    public Pair analyze(ProjectProperty projectProperty) {
        assert null == projectProperty : "Project property can not be null.";

        PairFactory pairFactory = new PairFactory();
        return pairFactory.newPair(projectProperty);
    }

}
