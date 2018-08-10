package cn.com.mirror.constant;

import lombok.Getter;

/**
 * @author piggy
 * @description
 * @date 18-8-9
 */
@Getter
public enum EdgeTypeEnum {
    CONTROL_EDGE("1", "control dependence"),
    SIMPLE_DATA_EDGE("2", "simple date dependence");

    private String key;
    private String desc;

    private EdgeTypeEnum(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public interface EdgeType {
        String CONTROL_EDGE = "CONTROL_EDGE";
        String SIMPLE_DATA_EDGE = "SIMPLE_DATA_EDGE";
    }
}
