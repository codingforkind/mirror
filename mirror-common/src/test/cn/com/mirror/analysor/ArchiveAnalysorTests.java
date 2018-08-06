package cn.com.mirror.analysor;

import cn.com.mirror.java.variable.Variable;
import cn.com.mirror.java.visitor.ControlDependenceVisitor;
import cn.com.mirror.project.Archive;
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
public class ArchiveAnalysorTests {
    private static final String TEST_PATH = "/home/piggy/work/test/mirror";
    private Archive archive;
    private ArchiveAnalysor archiveAnalysor;

    public void init() {
        this.archiveAnalysor = new ArchiveAnalysor();
        this.archive = archiveAnalysor.targetAnalyze(TEST_PATH);
    }

    @Test
    public void testControlDependenceVisitor() {
        init();

        archive.getTargets().stream().forEach(targetPah -> {
            ControlDependenceVisitor controlDependenceVisitor = new ControlDependenceVisitor();
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

        for (Map.Entry<String, Map<Integer, Set<Variable>>> entry : archive.getMappedVars().entrySet()) {
            log.info("Target path: {}", entry.getKey());

            for (Map.Entry<Integer, Set<Variable>> tm : entry.getValue().entrySet()) {
                log.info("Line: {}", tm.getKey());
                tm.getValue().stream().forEach(var -> {
                    System.out.println(var.getLineNum() + " "
                            + var.getName() + " "
                            + var.getVariableType() + " "
                            + var.isField());
                });
            }
            break;
        }

    }
}