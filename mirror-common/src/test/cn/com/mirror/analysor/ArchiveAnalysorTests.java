package cn.com.mirror.analysor;

import cn.com.mirror.project.Archive;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

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
        log.info("{}", archive.getPackages());
        log.info("{}", archive.getClasses());
        log.info("{}", archive.getTargets());

    }
}
