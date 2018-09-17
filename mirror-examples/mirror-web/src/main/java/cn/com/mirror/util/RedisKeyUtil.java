package cn.com.mirror.util;

public class RedisKeyUtil {

    public static final String genPrjKey(String userId, String prjName, String accessCode) {
        return userId + ":PRJ:" + accessCode;
    }

    public static final String genPrjAccessCodeKey(String userId) {
        return userId + ":accessCode";
    }
}
