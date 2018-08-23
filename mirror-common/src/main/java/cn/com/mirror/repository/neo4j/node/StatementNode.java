/**
 *
 */
package cn.com.mirror.repository.neo4j.node;

import cn.com.mirror.constant.EdgeType;
import cn.com.mirror.project.unit.element.Statement;
import lombok.Data;
import org.apache.http.util.Asserts;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * @author Piggy
 * @description
 */
@Data
@NodeEntity(label = "a line of code in target file")
public class StatementNode extends BaseNode {
    private static final long serialVersionUID = 1L;

    @Relationship(type = EdgeType.TYPE.STAT_TO_MTD)
    private MethodNode toMethod;

    @Relationship(type = EdgeType.TYPE.FID_TO_CLS)
    private ClassNode toClass;


    public StatementNode(Integer startLineNum,
                         String targetPath,
                         Integer endLineNum,
                         String content,
                         String packageName) {

        super(startLineNum, targetPath, endLineNum, content, packageName);
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
