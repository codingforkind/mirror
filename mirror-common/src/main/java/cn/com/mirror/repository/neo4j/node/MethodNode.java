/**
 *
 */
package cn.com.mirror.repository.neo4j.node;

import cn.com.mirror.constant.EdgeType;
import cn.com.mirror.project.unit.element.Method;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.jdt.core.dom.ASTNode;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

/**
 * @author Piggy
 * @description
 * @since 2018年4月19日
 */
@Getter
@Setter
@NodeEntity(label = "a method in target")
public class MethodNode extends BaseNode {
    private static final long serialVersionUID = 1L;

    @Property(name = "method name")
    private String methodName;

    @Property(name = "method end line num")
    private Integer methodEndLineNum;

    @Property(name = "method content")
    private String methodContent;

    @Property(name = "current method belongs to this class")
    @Relationship(type = EdgeType.EDGE_TYPE.METHOD_TO_CLASS_CTRL_EDGE)
    private ClassNode cls;

    MethodNode() {
    }

    MethodNode(Integer startLineNum,
               String targetPath,
               String methodName,
               Integer methodEndLineNum,
               ASTNode methodContent) {

        this.methodName = methodName;
        this.methodEndLineNum = methodEndLineNum;
        this.methodContent = methodContent.toString();
    }

    private static MethodNode instance(Method method) {
        return new MethodNode(method.getStartLineNum(),
                method.getName(),
                method.getEndLineNum(),
                method.getMethodDeclaration());
    }

}
