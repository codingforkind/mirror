package cn.com.mirror.analysor;

import cn.com.mirror.project.Archive;
import cn.com.mirror.variable.Variable;
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
public class ArchiveAnalysorTests {
    @Test
    public void test1() {
        ArchiveAnalysor archiveAnalysor = new ArchiveAnalysor();
        log.info("Start test");
        Archive archive = archiveAnalysor.targetAnalyze("/home/piggy/work/test/mirror");
//        log.info("{}", archive.getPackages());
//        log.info("{}", archive.getClasses());
//        log.info("{}", archive.getTargets());

        for (Map.Entry<String, Set<Variable>> entry : archive.getVariables().entrySet()) {
            log.info("Target path: {}", entry.getKey());
            log.info("Variables: {}", entry.getValue());
        }

    }
}
