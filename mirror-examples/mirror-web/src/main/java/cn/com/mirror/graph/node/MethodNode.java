/**
 *
 */
package cn.com.mirror.graph.node;

import cn.com.mirror.constant.EdgeType;
import cn.com.mirror.constant.ElementTypeEnum;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Piggy
 * @description
 * @since 2018年4月19日
 */
@Getter
@Setter
@NodeEntity(label = "method")
public class MethodNode extends BaseNode {
    private static final long serialVersionUID = 1L;

    @Relationship(type = EdgeType.TYPE.STAT_TO_MTD, direction = Relationship.INCOMING)
    private Set<StatementNode> statementNodes;

    MethodNode(Integer startLineNum,
               String targetPath,
               Integer endLineNum,
               String content,
               String packageName,
               ElementTypeEnum nodeType) {

        super(startLineNum, targetPath, endLineNum, content, packageName, nodeType);

        this.statementNodes = new HashSet<>();
    }

    public void addStatementNode(StatementNode statementNode) {
        this.statementNodes.add(statementNode);
    }
}
