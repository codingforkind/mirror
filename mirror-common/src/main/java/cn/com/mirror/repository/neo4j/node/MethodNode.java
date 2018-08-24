/**
 *
 */
package cn.com.mirror.repository.neo4j.node;

import cn.com.mirror.constant.EdgeType;
import cn.com.mirror.project.unit.element.Method;
import lombok.Data;
import org.apache.http.util.Asserts;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

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

    @Relationship(type = EdgeType.TYPE.MTD_TO_CLS)
    private ClassNode cls;

    public MethodNode(Integer startLineNum,
                      String targetPath,
                      Integer endLineNum,
                      String content,
                      String packageName,
                      String name) {

        super(startLineNum, targetPath, endLineNum, content, packageName);

        this.name = name;
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
}
