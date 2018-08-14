package cn.com.mirror.constant;

import lombok.Getter;

/**
 * @author piggy
 * @description
 * @date 18-8-9
 */
@Getter
public enum EdgeType {
    CONTROL_EDGE("1", "control dependence"),
    STATEMENT_TO_METHOD_CTRL_EDGE("2", "statement to method control edge"),
    METHOD_TO_CLASS_CTRL_EDGE("3", "method to class control edge"),
    SIMPLE_DATA_EDGE("99", "simple date dependence");

    private String key;
    private String desc;

    private EdgeType(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public interface EDGE_TYPE {
        String NODE_TO_NODE_CTRL_EDGE = "NODE_TO_NODE_CTRL_EDGE";
        String STATEMENT_TO_METHOD_CTRL_EDGE = "STATEMENT_TO_METHOD_CTRL_EDGE";
        String METHOD_TO_CLASS_CTRL_EDGE = "METHOD_TO_CLASS_CTRL_EDGE";
        String SIMPLE_DATA_EDGE = "SIMPLE_DATA_EDGE";
    }
}
