package cn.com.mirror.reflect;

import cn.com.mirror.exceptions.UnitException;
import cn.com.mirror.project.unit.element.Base;
import cn.com.mirror.repository.neo4j.node.BaseNode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author piggy
 * @description
 * @date 18-8-27
 */
@Data
@Slf4j
public class NodeFactory {

    private Map<Base, BaseNode> nodeCache = new HashMap<>();

    public final BaseNode newNode(Base base) {
        BaseNode baseNode = this.nodeCache.get(base);
        if (null != baseNode) {
            return baseNode;
        }

        baseNode = BaseNode.instance(base);
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
