package cn.com.mirror.analyser;

import cn.com.mirror.project.config.ProjectProperty;
import cn.com.mirror.project.unit.Unit;
import cn.com.mirror.project.unit.UnitFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author piggy
 * @description
 * @date 18-7-25
 */
@Slf4j
public class UnitAnalyser {

    public Unit analyze(ProjectProperty projectProperty) {
        assert null == projectProperty : "Project property can not be null.";

        UnitFactory unitFactory = new UnitFactory();
        return unitFactory.newUnit(projectProperty);
    }

}
