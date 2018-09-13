package cn.com.mirror.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class RedisUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void opSetStrVal(String key, String val) {
        log.debug("Set val: [{}] for key: [{}].", val, key);
        redisTemplate.opsForValue().set(key, val);
    }

    public void opSetStrValWithMinutes(String key, String val, int minutes) {
        log.debug("Set val: [{}] for key: [{}] alive in [{}] minutes.", val, key, minutes);
        redisTemplate.opsForValue().set(key, val, minutes, TimeUnit.MINUTES);
    }

    public void opSetStrValWithSeconds(String key, String val, int seconds) {
        log.debug("Set val: [{}] for key: [{}] alive in [{}] seconds.", val, key, seconds);
        redisTemplate.opsForValue().set(key, val, seconds, TimeUnit.SECONDS);
    }

    public void opSetStrValWithDays(String key, String val, int days) {
        log.debug("Set val: [{}] for key: [{}] alive in [{}] days.", val, key, days);
        redisTemplate.opsForValue().set(key, val, days, TimeUnit.DAYS);
    }

    public void opSetStrValForOneDay(String key, String val) {
        opSetStrValWithDays(key, val, 1);
    }

    public boolean isExists(String key) {
        Boolean reVal = redisTemplate.hasKey(key);
        return null == reVal ? false : reVal.booleanValue();
    }

    public String opGetStrVal(String key) {
        if (isExists(key)) {
            return redisTemplate.opsForValue().get(key);
        }
        return null;
    }

    public Long increase(String key) {
        return redisTemplate.boundValueOps(key).increment(1);
    }
}