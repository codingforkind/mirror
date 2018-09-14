package cn.com.mirror.util;

public class RedisKeyUtil {

    public static final String genPrjKey(String userId, String prjName) {
        return userId + ":" + prjName;
    }
}
