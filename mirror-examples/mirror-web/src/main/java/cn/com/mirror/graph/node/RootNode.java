package cn.com.mirror.graph.node;

import cn.com.mirror.constant.EdgeType;
import cn.com.mirror.constant.ElementTypeEnum;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.Relationship;

/**
 * @author piggy
 * @description
 * @date 18-8-24
 */
@Getter
@Setter
public class RootNode extends BaseNode {
    @Relationship(type = EdgeType.TYPE.TARGET_ROOT, direction = Relationship.INCOMING)
    private ClassNode targetNode;

    RootNode(Integer startLineNum,
             String targetPath,
             Integer endLineNum,
             String content,
             String packageName,
             ElementTypeEnum nodeType) {

        super(startLineNum, targetPath, endLineNum, content, packageName, nodeType);
    }
}
