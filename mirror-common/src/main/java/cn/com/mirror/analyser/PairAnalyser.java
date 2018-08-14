package cn.com.mirror.analyser;

import cn.com.mirror.project.pair.Pair;
import cn.com.mirror.project.pair.factory.PairFactory;
import cn.com.mirror.repository.code.CodeLoader;
import cn.com.mirror.repository.code.LocalLoader;

/**
 * @author piggy
 * @description
 * @date 18-8-14
 */
public class PairAnalyser {
    public Pair analyze() {
        PairFactory pairFactory =  new PairFactory();
        CodeLoader codeRepository = new LocalLoader();
        return pairFactory.newPair(codeRepository.getRepositoryUrl());
    }
}
