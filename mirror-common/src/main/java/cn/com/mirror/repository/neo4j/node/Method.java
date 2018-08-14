/**
 *
 */
package cn.com.mirror.repository.neo4j.node;

import cn.com.mirror.constant.EdgeType;
import cn.com.mirror.utils.BeanUtils;
import org.eclipse.jdt.core.dom.ASTNode;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Objects;

/**
 * @author Piggy
 * @description
 * @since 2018年4月19日
 */
@Getter
@Setter
@NodeEntity(label = "a method in target")
public class Method extends Class {
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
    private Class cls;

    Method() {
    }

    Method(String methodName,
           Integer methodStartLineNum,
           Integer methodEndLineNum,
           ASTNode methodContent) {

        this.methodName = methodName;
        this.methodStartLineNum = methodStartLineNum;
        this.methodEndLineNum = methodEndLineNum;
        this.methodContent = methodContent.toString();
    }

    public static Method instance(Class classNode,
                                  String methodName,
                                  Integer methodStartLineNum,
                                  Integer methodEndLineNum,
                                  ASTNode methodContent) {

        Method method = new Method(methodName, methodStartLineNum, methodEndLineNum, methodContent);
        BeanUtils.copyProperties(method, classNode);
        return method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        
        Method method = (Method) o;
        return Objects.equals(methodName, method.methodName) &&
                Objects.equals(methodStartLineNum, method.methodStartLineNum) &&
                Objects.equals(methodEndLineNum, method.methodEndLineNum) &&
                Objects.equals(methodContent, method.methodContent) &&
                Objects.equals(cls, method.cls);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), methodName, methodStartLineNum, methodEndLineNum, methodContent, cls);
    }
}
