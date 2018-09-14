package cn.com.mirror.util;

public class RedisKeyUtil {

    public static final String genPrjKey(String userId, String prjName) {
        return userId + ":prj:" + prjName;
    }

    public static final String genPrjAccessCodeKey(String userId) {
        return userId + ":accessCode";
    }
}
