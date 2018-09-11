package cn.com.mirror;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = WebApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class RedisTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test1() {
        log.debug("Redis tests!");
        stringRedisTemplate.opsForValue().set("TEST1", "TEST VALUE");
    }

}
