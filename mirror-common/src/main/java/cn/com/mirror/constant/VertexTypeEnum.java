package cn.com.mirror.constant;

import lombok.Getter;

/**
 * @author piggy
 * @description
 * @date 18-8-23
 */
@Getter
public enum VertexTypeEnum {
    STATEMENT(1, "statement"),
    FIELD(2, "field"),
    METHOD(3, "method"),
    CLASS(4, "class"),
    PACKAGE(5, "package");

    private Integer key;
    private String desc;

    VertexTypeEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }
}
