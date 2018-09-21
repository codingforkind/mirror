package cn.com.mirror.reflect;

import cn.com.mirror.Mirror;
import org.junit.Test;

/**
 * @author piggy
 * @description
 * @date 18-8-16
 */
public class MirrorTests {
    @Test
    public void testConstructSimple() {
        Mirror mirror = new Mirror();
        mirror.mappingVertex2GraphNode();
    }
}
