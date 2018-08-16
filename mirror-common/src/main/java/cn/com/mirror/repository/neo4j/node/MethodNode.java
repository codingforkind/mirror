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

import java.io.Serializable;

/**
 * @author Piggy
 * @description
 * @since 2018年4月19日
 */
@Getter
@Setter
@NodeEntity(label = "a method in target")
public class MethodNode implements Serializable {
    private static final long serialVersionUID = 1L;

    @Property(name = "method name")
    private String methodName;

    @Property(name = "method start line num")
    private Integer methodStartLineNum;

    @Property(name = "method end line num")
    private Integer methodEndLineNum;

    @Property(name = "method content")
    private String methodContent;

    @Property(name = "current method belongs to this class")
    @Relationship(type = EdgeType.EDGE_TYPE.METHOD_TO_CLASS_CTRL_EDGE)
    private ClassNode cls;

    MethodNode() {
    }

    MethodNode(String methodName,
               Integer methodStartLineNum,
               Integer methodEndLineNum,
               ASTNode methodContent) {

        this.methodName = methodName;
        this.methodStartLineNum = methodStartLineNum;
        this.methodEndLineNum = methodEndLineNum;
        this.methodContent = methodContent.toString();
    }

    private static MethodNode instance(Method method) {
        return new MethodNode(method.getName(),
                method.getStartLineNum(),
                method.getEndLineNum(),
                method.getMethodDeclaration());
    }

}
