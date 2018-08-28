/**
 *
 */
package cn.com.mirror.repository.neo4j.node;

import cn.com.mirror.constant.EdgeType;
import cn.com.mirror.constant.ElementTypeEnum;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * Statement nodes are the leafs in the graph
 *
 * @author Piggy
 * @description
 */
@NodeEntity(label = "statement")
public class StatementNode extends BaseNode {
    private static final long serialVersionUID = 1L;

    @Relationship(type = EdgeType.TYPE.CTRL_EDGE, direction = Relationship.INCOMING)
    private Set<StatementNode> statementNodes;

    StatementNode(Integer startLineNum,
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
