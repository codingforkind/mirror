package cn.com.mirror.analyser;

import cn.com.mirror.project.unit.Unit;
import cn.com.mirror.project.unit.UnitFactory;
import cn.com.mirror.repository.code.CodeLoader;
import cn.com.mirror.repository.code.LocalLoader;
import lombok.extern.slf4j.Slf4j;

/**
 * @author piggy
 * @description
 * @date 18-7-25
 */
@Slf4j
public class UnitAnalyser {


    public Unit analyze() {
        UnitFactory unitFactory = new UnitFactory();
        CodeLoader codeRepository = new LocalLoader();
        return unitFactory.newUnit(codeRepository.getRepositoryUrl());
    }

}
