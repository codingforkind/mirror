package cn.com.mirror.reflect;

import cn.com.mirror.exceptions.UnitException;
import cn.com.mirror.project.unit.element.*;
import cn.com.mirror.project.unit.element.Class;
import cn.com.mirror.repository.neo4j.node.*;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author piggy
 * @description
 * @date 18-8-27
 */
@Data
public class NodeFactory {

    private Map<Base, BaseNode> nodeCache = new HashMap<>();

    public final BaseNode newNode(Base base) {
        BaseNode baseNode = this.nodeCache.get(base);
        if (null != baseNode) {
            return baseNode;
        }

        switch (base.getElementTypeEnum()) {
            case ROOT: {
                baseNode = RootNode.instance((Root) base);
                break;
            }
            case CLASS: {
                baseNode = ClassNode.instance((Class) base);
                break;
            }
            case METHOD: {
                baseNode = MethodNode.instance((Method) base);
                break;
            }
            case STATEMENT: {
                baseNode = StatementNode.instance((Statement) base);
                break;
            }
        }

        if (null == baseNode) {
            throw new UnitException("Element type can not match.");
        }

        this.nodeCache.put(base, baseNode);
        return baseNode;
    }


    public BaseNode updateNode(Base base, BaseNode baseNode) {
        BaseNode cachedNode = this.nodeCache.get(base);
        if (null == cachedNode) {
            throw new UnitException("Base element's node has not cached before.");
        }

        if (null == baseNode) {
            throw new UnitException("BaseNode can not be null when trying to cache it.");
        }

        this.nodeCache.put(base, baseNode);
        return baseNode;
    }

}
