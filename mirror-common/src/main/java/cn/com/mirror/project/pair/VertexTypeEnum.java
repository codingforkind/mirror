package cn.com.mirror.project.pair;

import lombok.Getter;

/**
 * @author piggy
 * @description
 * @date 18-8-23
 */
@Getter
public enum VertexTypeEnum {
    STATEMENT(1, "statement"),
    METHOD(2, "method");

    private Integer key;
    private String desc;

    VertexTypeEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }
}
