/**
 *
 */
package cn.com.mirror.repository.neo4j.node;

import cn.com.mirror.utils.BeanUtils;
import lombok.Data;
import org.eclipse.jdt.core.dom.ASTNode;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import java.util.Objects;

/**
 * @author Piggy
 * @description
 * @since 2018年4月19日
 */
@Data
@NodeEntity(label = "a class in target")
public class ClassNode extends BaseNode {
    private static final long serialVersionUID = 1L;

    @Property(name = "package name")
    private String packageName;

    @Property(name = "class name")
    private String className;

    @Property(name = "class start line num")
    private Integer classStartLineNum;

    @Property(name = "class end line num")
    private Integer classEndLineNum;

    @Property(name = "class content")
    private String classContent;

    ClassNode() {
    }

    ClassNode(String packageName,
              String className,
              Integer classStartLineNum,
              Integer classEndLineNum,
              ASTNode classContent) {

        this.packageName = packageName;
        this.className = className;
        this.classStartLineNum = classStartLineNum;
        this.classEndLineNum = classEndLineNum;
        this.classContent = classContent.toString();
    }

    public static ClassNode instance(BaseNode node,
                                     String packageName,
                                     String className,
                                     Integer classStartLineNum,
                                     Integer classEndLineNum,
                                     ASTNode classContent) {

        ClassNode classNode = new ClassNode(packageName, className, classStartLineNum, classEndLineNum, classContent);
        BeanUtils.copyProperties(classNode, node);
        return classNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ClassNode aClass = (ClassNode) o;
        return Objects.equals(packageName, aClass.packageName) &&
                Objects.equals(className, aClass.className) &&
                Objects.equals(classStartLineNum, aClass.classStartLineNum) &&
                Objects.equals(classEndLineNum, aClass.classEndLineNum) &&
                Objects.equals(classContent, aClass.classContent);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), packageName, className, classStartLineNum, classEndLineNum, classContent);
    }
}
