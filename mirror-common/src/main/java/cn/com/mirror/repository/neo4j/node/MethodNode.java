/**
 *
 */
package cn.com.mirror.repository.neo4j.node;

import cn.com.mirror.constant.EdgeType;
import cn.com.mirror.project.unit.element.Method;
import lombok.Getter;
import org.apache.http.util.Asserts;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

/**
 * @author Piggy
 * @description
 * @since 2018年4月19日
 */
@Getter
@NodeEntity(label = "a method in target")
public class MethodNode extends BaseNode {
    private static final long serialVersionUID = 1L;

    @Property(name = "method name")
    private String name;

    @Relationship(type = EdgeType.EDGE_TYPE.METHOD_TO_CLASS_CTRL_EDGE)
    private ClassNode cls;

    public MethodNode(Integer startLineNum,
                      String targetPath,
                      Integer endLineNum,
                      String content,
                      String packageName,
                      String name,
                      ClassNode cls) {

        super(startLineNum, targetPath, endLineNum, content, packageName);

        this.name = name;
        this.cls = cls;
    }

    public static final MethodNode instance(Method method) {
        Asserts.notNull(method, "Method element can not be null.");

        return new MethodNode(method.getStartLineNum(),
                method.getTargetPath(),
                method.getEndLineNum(),
                method.getContent(),
                method.getPackageName(),
                method.getName(),
                ClassNode.instance(method.getInClass()));
    }
}
