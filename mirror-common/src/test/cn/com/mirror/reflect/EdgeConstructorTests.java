package cn.com.mirror.reflect;

import org.junit.Test;

/**
 * @author piggy
 * @description
 * @date 18-8-16
 */
public class EdgeConstructorTests {
    @Test
    public void test1() {
        EdgeConstructor edgeConstructor = new EdgeConstructor();
        edgeConstructor.preConstruct();
        edgeConstructor.construct();
    }
}
