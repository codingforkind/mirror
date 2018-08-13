package cn.com.mirror.analyser;

import cn.com.mirror.project.unit.Unit;
import cn.com.mirror.project.unit.factory.UnitFactory;
import cn.com.mirror.repository.code.CodeRepository;
import cn.com.mirror.repository.code.LocalRepository;
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
        CodeRepository codeRepository = new LocalRepository();
        return unitFactory.newUnit(codeRepository.getRepositoryUrl());
    }

}
