/**
 *
 */
package cn.com.mirror.repository.neo4j.node;

import cn.com.mirror.constant.ElementTypeEnum;
import cn.com.mirror.project.unit.element.Statement;
import lombok.Data;
import org.apache.http.util.Asserts;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Statement nodes are the leafs in the graph
 * @author Piggy
 * @description
 */
@Data
@NodeEntity(label = "statement")
public class StatementNode extends BaseNode {
    private static final long serialVersionUID = 1L;

    public StatementNode(Integer startLineNum,
                         String targetPath,
                         Integer endLineNum,
                         String content,
                         String packageName) {

        super(startLineNum, targetPath, endLineNum, content, packageName, ElementTypeEnum.STATEMENT);
    }

    public static final StatementNode instance(Statement statement) {
        Asserts.notNull(statement, "Statement element can not be null.");

        return new StatementNode(statement.getStartLineNum(),
                statement.getTargetPath(),
                statement.getEndLineNum(),
                statement.getContent(),
                statement.getPackageName());
    }
}
