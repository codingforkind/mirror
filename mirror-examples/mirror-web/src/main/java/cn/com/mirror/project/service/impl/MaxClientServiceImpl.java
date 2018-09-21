package cn.com.mirror.project.service.impl;

import cn.com.mirror.config.properties.MirrorProperties;
import cn.com.mirror.project.service.MaxClientService;
import cn.com.mirror.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaxClientServiceImpl implements MaxClientService {
    @Autowired
    private MirrorProperties mirrorProperties;

    @Autowired
    private RedisUtil redisUtil;

    private final static String CLIENT_COUNT = "CLIENT_COUNT";

    @Override
    public boolean maxCliEnabled() {
        return mirrorProperties.getMaxEnable();
    }

    @Override
    public boolean maxCliNumReached() {
        if (!maxCliEnabled()) return false;

        return false;
    }

    @Override
    public void increaseClientCount() {
        if (!maxCliEnabled()) return;

        String countStr = redisUtil.opGetStrVal(CLIENT_COUNT);

        if (StringUtils.isEmpty(countStr)) {
            redisUtil.opSetStrValForOneDay(CLIENT_COUNT, Integer.toString(1));
        } else {
            Long count = Long.valueOf(countStr);
            if (count >= mirrorProperties.getMaxCliNum()) {
                throw new RuntimeException("Access src is already run out for today");
            } else {
                count++; // TODO xyz this is not thread safe
                redisUtil.opSetStrValForOneDay(CLIENT_COUNT, Long.toString(count));
            }
        }
    }
}
