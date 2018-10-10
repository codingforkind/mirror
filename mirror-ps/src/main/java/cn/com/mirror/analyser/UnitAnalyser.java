package cn.com.mirror.analyser;

import cn.com.mirror.project.config.ProjectProperty;
import cn.com.mirror.project.unit.Unit;
import cn.com.mirror.project.unit.UnitFactory;
import cn.com.mirror.project.code.CodeLoader;
import cn.com.mirror.project.code.LocalLoader;
import lombok.extern.slf4j.Slf4j;

/**
 * @author piggy
 * @description
 * @date 18-7-25
 */
@Slf4j
public class UnitAnalyser {

    public Unit analyze(ProjectProperty projectProperty) {
        UnitFactory unitFactory = new UnitFactory();

        Unit unit = null;
        if (null == projectProperty) {
            unit = unitFactory.newUnit(LocalLoader.getPrjProperty().getUrl());
        } else {
            unit = unitFactory.newUnit(projectProperty.getUrl());
        }

        return unit;
    }

}
