package cn.com.mirror.analyser;

import cn.com.mirror.analyser.visitor.ControlEdgeVisitor;
import cn.com.mirror.project.unit.Unit;
import cn.com.mirror.project.unit.element.Statement;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.Test;

import java.util.Map;

/**
 * @author piggy
 * @description
 * @date 18-7-26
 */
@Slf4j
public class UnitAnalysorTests {
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
    public void testVariables() {
        init();
//        log.info("{}", archive.getPackages());
//        log.info("{}", archive.getClasses());
//        log.info("{}", archive.getTargets());

//        for (Map.Entry<String, Set<Variable>> entry : archive.getVariables().entrySet()) {
//            log.info("Target path: {}", entry.getKey());
//            log.info("Variables: {}", entry.getValue());
//        }

        for (Map.Entry<String, Map<Integer, Statement>> entry : archive.getStatements().entrySet()) {
            log.info("Target path: {}", entry.getKey());

            for (Map.Entry<Integer, Statement> tm : entry.getValue().entrySet()) {
                log.info("BaseNode: {}", tm.getKey());
                tm.getValue().getVarsInStat().stream().forEach(var -> {
                    System.out.println(var.getLineNum() + " "
                            + var.getName() + " "
                            + var.getVariableType() + " "
                            + var.isFieldFlag());
                });
            }
            break;
        }

    }
}
