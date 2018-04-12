package cn.com.cx.ps;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.com.cx.mirror.web.MirrorWebApplication;
import cn.com.cx.mirror.web.util.RedisUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= MirrorWebApplication.class)
public class TestMirrorRestUtil {

    @Autowired
    private RedisUtils mirrorRedisUtil;

    @Test
    public void testString() {
        mirrorRedisUtil.setStringVal("mirror", "A code mirror.");
    }
}
