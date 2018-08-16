/**
 *
 */
package cn.com.mirror.repository.neo4j.node;

import cn.com.mirror.constant.EdgeType;
import lombok.Getter;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

/**
 * @author Piggy
 * @description
 */
@Getter
@NodeEntity(label = "a line of code in target file")
public class StatementNode extends BaseNode {
    private static final long serialVersionUID = 1L;

    @Property(name = "current statements belongs to this method")
    @Relationship(type = EdgeType.EDGE_TYPE.STATEMENT_TO_METHOD_CTRL_EDGE)
    private MethodNode method;


    public StatementNode(Integer startLineNum,
                         String targetPath,
                         Integer endLineNum,
                         String content,
                         String packageName,
                         MethodNode method) {

        super(startLineNum, targetPath, endLineNum, content, packageName);

        this.method = method;
    }
}