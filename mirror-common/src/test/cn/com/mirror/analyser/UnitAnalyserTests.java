package cn.com.mirror.analyser;

import cn.com.mirror.project.unit.Unit;
import cn.com.mirror.project.unit.element.Class;
import cn.com.mirror.project.unit.element.Method;
import cn.com.mirror.project.unit.element.Statement;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

/**
 * @author piggy
 * @description
 * @date 18-7-26
 */
@Slf4j
public class UnitAnalyserTests {
    private Unit unit;
    private UnitAnalyser unitAnalyser;

    public void init() {
        this.unitAnalyser = new UnitAnalyser();
        this.unit = unitAnalyser.analyze();
    }

    @Test
    public void testCls() {
        init();

        for (Map.Entry<String, Set<Class>> entry : unit.getClasses().entrySet()) {
            log.debug("Target path: {}", entry.getKey());

            entry.getValue().stream().forEach(cls -> {
                log.debug("Name: {}, start: {}, end: {}",
                        cls.getName(), cls.getStartLineNum(), cls.getEndLineNum());
                cls.getFields().stream().forEach(field -> {
                    log.debug("FIELD lineNum: {}, name: {}", field.getLineNum(), field.getName());
                });
            });

        }

    }

    @Test
    public void testMtd() {
        init();

        for (Map.Entry<String, Set<Method>> entry : unit.getMethods().entrySet()) {
            log.debug("Target path: {}", entry.getKey());

            entry.getValue().stream().forEach(mtd -> {
                log.debug("Start: {}, end: {}",
                        mtd.getStartLineNum(), mtd.getEndLineNum());
                mtd.getParams().stream().forEach(param -> {
                    log.debug(" PARAM lineNum: {}, name: {}", param.getLineNum(), param.getName());
                });
            });
        }
    }

    @Test
    public void testVariables() {
        init();

        for (Map.Entry<String, Map<Integer, Statement>> entry : unit.getStatements().entrySet()) {
            log.info("Target path: {}", entry.getKey());

            for (Map.Entry<Integer, Statement> tm : entry.getValue().entrySet()) {
                tm.getValue().getVariables().stream().forEach(var -> {
                    log.debug("LineNum: {}, Name: {}, VarType: {}, isField: {}",
                            var.getLineNum(), var.getName(), var.getVariableType(), var.isFieldFlag());
                });
            }
            break;
        }

    }
}
