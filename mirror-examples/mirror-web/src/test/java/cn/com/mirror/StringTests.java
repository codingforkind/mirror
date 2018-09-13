package cn.com.mirror;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class StringTests {

    @Test
    public void test1() {
        String originalFileName = "test1.test.txt";
        log.debug("file name: {}, type: {}",
                originalFileName.substring(0, originalFileName.lastIndexOf(".")),
                originalFileName.substring(originalFileName.lastIndexOf(".") + 1));
    }
}
