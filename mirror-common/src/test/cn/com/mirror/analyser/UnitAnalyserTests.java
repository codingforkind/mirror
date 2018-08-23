package cn.com.mirror.analyser;

import cn.com.mirror.analyser.visitor.ControlEdgeVisitor;
import cn.com.mirror.project.unit.Unit;
import cn.com.mirror.project.unit.element.Method;
import cn.com.mirror.project.unit.element.Statement;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jdt.core.dom.CompilationUnit;
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
    private Unit archive;
    private UnitAnalyser archiveAnalysor;

    public void init() {
        this.archiveAnalysor = new UnitAnalyser();
        this.archive = archiveAnalysor.analyze();
    }

    @Test
    public void testControlDependenceVisitor() {
        init();

        archive.getTargets().stream().forEach(targetPah -> {
            ControlEdgeVisitor controlDependenceVisitor = new ControlEdgeVisitor();
            CompilationUnit compilationUnit = archive.getCompilationUnits().get(targetPah);
            compilationUnit.accept(controlDependenceVisitor);
            log.info("Target path: {}", targetPah);
            log.info("Control edges: {}", controlDependenceVisitor.getControlEdges());
        });
    }

    @Test
    public void testClasses() {
        init();

        for (Map.Entry<String, Set<Method>> entry : archive.getMethods().entrySet()) {
            log.debug("Target path: {}", entry.getKey());

            entry.getValue().stream().forEach(mtd -> {
                log.debug("Start: {}, end: {}", mtd.getStartLineNum(), mtd.getEndLineNum());
            });
        }
    }

    @Test
    public void testVariables() {
        init();

        for (Map.Entry<String, Map<Integer, Statement>> entry : archive.getStatements().entrySet()) {
            log.info("Target path: {}", entry.getKey());

            for (Map.Entry<Integer, Statement> tm : entry.getValue().entrySet()) {
                Method mtd = tm.getValue().getInMethod();
                if (null != mtd) {
                    log.debug("IN METHOD start: {}, end: {}, name: {}", mtd.getStartLineNum(),
                            mtd.getEndLineNum(), mtd.getName());
                } else {
                    log.debug("NULL METHOD");
                }
                tm.getValue().getVariables().stream().forEach(var -> {
                    log.debug("LineNum: {}, Name: {}, VarType: {}, isField: {}",
                            var.getLineNum(), var.getName(), var.getVariableType(), var.isFieldFlag());
                });
            }
            break;
        }

    }
}
