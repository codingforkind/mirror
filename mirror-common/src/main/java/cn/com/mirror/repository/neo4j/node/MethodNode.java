/**
 *
 */
package cn.com.mirror.repository.neo4j.node;

import cn.com.mirror.constant.EdgeType;
import cn.com.mirror.constant.NodeTypeEnum;
import cn.com.mirror.project.unit.element.Method;
import lombok.Data;
import org.apache.http.util.Asserts;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Piggy
 * @description
 * @since 2018年4月19日
 */
@Data
@NodeEntity(label = "method")
public class MethodNode extends BaseNode {
    private static final long serialVersionUID = 1L;

    @Property(name = "method name")
    private String name;

    @Relationship(type = EdgeType.TYPE.STAT_TO_MTD, direction = Relationship.INCOMING)
    private Set<StatementNode> statementNodes;

    public MethodNode(Integer startLineNum,
                      String targetPath,
                      Integer endLineNum,
                      String content,
                      String packageName,
                      String name) {

        super(startLineNum, targetPath, endLineNum, content, packageName, NodeTypeEnum.METHOD);

        this.name = name;
        this.statementNodes = new HashSet<>();
    }

    public static final MethodNode instance(Method method) {
        Asserts.notNull(method, "Method element can not be null.");

        return new MethodNode(method.getStartLineNum(),
                method.getTargetPath(),
                method.getEndLineNum(),
                method.getContent(),
                method.getPackageName(),
                method.getName());
    }

    public void touchStatementNode(StatementNode statementNode) {
        this.statementNodes.add(statementNode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        MethodNode that = (MethodNode) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), name);
    }
}
