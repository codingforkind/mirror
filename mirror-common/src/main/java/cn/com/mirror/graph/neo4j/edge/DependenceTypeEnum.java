package cn.com.mirror.graph.neo4j.edge;

import lombok.Getter;

/**
 * @author piggy
 * @description
 * @date 18-8-9
 */
@Getter
public enum DependenceTypeEnum {
    CONTROL_EDGE("1", "control dependence"),
    SIMPLE_DATA_EDGE("2", "simple date dependence");

    private String key;
    private String desc;

    private DependenceTypeEnum(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }
}
