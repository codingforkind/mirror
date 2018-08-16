/**
 *
 */
package cn.com.mirror.repository.neo4j.node;

import cn.com.mirror.project.unit.element.Class;
import lombok.Data;
import org.eclipse.jdt.core.dom.ASTNode;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import java.io.Serializable;

/**
 * @author Piggy
 * @description
 * @since 2018年4月19日
 */
@Data
@NodeEntity(label = "a class in target")
public class ClassNode implements Serializable {
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

    public static ClassNode instance(Class cls,
                                     Integer lineNum,
                                     String targetPath) {

        return new ClassNode(cls.getPackageName(),
                cls.getName(),
                cls.getClsStartLineNum(),
                cls.getClsEndLineNum(),
                cls.getTypeDeclaration());
    }

}
