package cn.com.mirror.project.service.impl;

import cn.com.mirror.project.service.MaxClientService;
import cn.com.mirror.project.service.ProjectInitService;
import cn.com.mirror.util.RedisUtil;
import cn.com.mirror.utils.EncryptUtils;
import cn.com.mirror.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProjectInitServiceImpl implements ProjectInitService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MaxClientService maxClientService;

    @Override
    public String getAccessCode(String userId) {

        if (StringUtils.isEmpty(userId)) {
            throw new RuntimeException("User id can not be null or empty");
        }

        String accessCode = redisUtil.opGetStrVal(userId);
        if (null == accessCode) {
            maxClientService.increaseClientCount();

            String salt = UUIDUtils.randomUUID();
            accessCode = EncryptUtils.sha256Encrypt(userId + salt);
            redisUtil.opSetStrValForOneDay(userId, accessCode);
        }
        return accessCode;
    }
}
