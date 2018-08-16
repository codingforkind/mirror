/**
 *
 */
package cn.com.mirror.repository.neo4j.node;

import cn.com.mirror.project.unit.element.Class;
import lombok.Data;
import org.eclipse.jdt.core.dom.ASTNode;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

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

    @Property(name = "class end line num")
    private Integer classEndLineNum;

    @Property(name = "class content")
    private String classContent;

    ClassNode() {
    }

    ClassNode(Integer startLineNum,
              String targetPath,
              String packageName,
              String className,
              Integer classEndLineNum,
              ASTNode classContent) {

        super(startLineNum, targetPath);

        this.packageName = packageName;
        this.className = className;
        this.classEndLineNum = classEndLineNum;
        this.classContent = classContent.toString();
    }

    public static ClassNode instance(Class cls) {

        return new ClassNode(cls.getClsStartLineNum(),
                cls.getFile(),
                cls.getPackageName(),
                cls.getName(),
                cls.getClsEndLineNum(),
                cls.getTypeDeclaration());
    }

}
