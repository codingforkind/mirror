package cn.com.cx.ps.mirror.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtils {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public void setStringVal(String key, String val) {
        this.redisTemplate.opsForValue().set(key, val);
    }

}
