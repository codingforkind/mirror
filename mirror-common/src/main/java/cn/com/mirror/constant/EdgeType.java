package cn.com.mirror.constant;

import lombok.Getter;

/**
 * @author piggy
 * @description
 * @date 18-8-9
 */
@Getter
public enum EdgeType {
    CTRL_EDGE("1", "control dependence"),
    STAT_TO_MTD("2", "statements to method control edge"),
    MTD_TO_CLS("3", "method to class control edge"),
    DATA_EDGE("99", "simple date dependence");

    private String key;
    private String desc;

    private EdgeType(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public interface TYPE {
        String CTRL_EDGE = "CTRL_EDGE";
        String STAT_TO_MTD = "STAT_TO_MTD";
        String MTD_TO_CLS = "MTD_TO_CLS";
        String DATA_EDGE = "DATA_EDGE";
    }
}
