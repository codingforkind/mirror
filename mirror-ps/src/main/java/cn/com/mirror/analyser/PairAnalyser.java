package cn.com.mirror.analyser;

import cn.com.mirror.project.config.ProjectProperty;
import cn.com.mirror.project.pair.Pair;
import cn.com.mirror.project.pair.factory.PairFactory;
import org.apache.commons.lang3.Validate;

/**
 * @author piggy
 * @description
 * @date 18-8-14
 */
public class PairAnalyser {

    public Pair analyze(ProjectProperty projectProperty) {
        Validate.notNull(projectProperty, "Project property can not be null.");
        PairFactory pairFactory = new PairFactory();
        return pairFactory.newPair(projectProperty);
    }

}
