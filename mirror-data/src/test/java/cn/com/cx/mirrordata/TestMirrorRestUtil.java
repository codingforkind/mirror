package cn.com.cx.mirrordata;

import cn.com.cx.mirrordata.redis.MirrorRedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMirrorRestUtil {

    @Autowired
    private MirrorRedisUtil mirrorRedisUtil;

    @Test
    public void testString() {
        mirrorRedisUtil.setStringVal("mirror", "A code mirror.");
    }
}
