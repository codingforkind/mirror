/**
 *
 */
package cn.com.mirror.constant;

import cn.com.mirror.exceptions.ConstantException;
import cn.com.mirror.exceptions.UnitException;
import lombok.Getter;

/**
 * @author Piggy
 * @description
 * @date 2018年4月27日
 */
@Getter
public enum ElementTypeEnum {
    ROOT("ROOT"), // package
    CLASS("CLASS"),
    METHOD("METHOD"),
    STATEMENT("STATEMENT");

    private String key;

    ElementTypeEnum(String key) {
        this.key = key;
    }

    public static ElementTypeEnum getNodeTypeEnum(String key) {
        for (ElementTypeEnum elementTypeEnum : ElementTypeEnum.values()) {
            if (elementTypeEnum.getKey().equals(key)) {
                return elementTypeEnum;
            }
        }
        throw new ConstantException("No node type match!");
    }

    public static final ElementTypeEnum vtxToNodeType(VertexTypeEnum vertexTypeEnum) {
        switch (vertexTypeEnum) {
            case METHOD: {
                return METHOD;
            }
            case FIELD:
            case STATEMENT: {
                return STATEMENT;
            }
            case CLASS: {
                return CLASS;
            }
            case PACKAGE: {
                return ROOT;
            }
            default:
                break;
        }
        throw new UnitException(vertexTypeEnum.getKey() + " vertex enum can mapping to node type.");
    }

}
