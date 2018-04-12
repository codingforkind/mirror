package cn.com.cx.ps;

import cn.com.cx.ps.mirror.MirrorServiceApplication;
import cn.com.cx.ps.mirror.common.utils.RedisUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= MirrorServiceApplication.class)
public class TestMirrorRestUtil {

    @Autowired
    private RedisUtils mirrorRedisUtil;

    @Test
    public void testString() {
        mirrorRedisUtil.setStringVal("mirror", "A code mirror.");
    }
}
