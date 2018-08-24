/**
 *
 */
package cn.com.mirror.repository.neo4j.node;

import cn.com.mirror.constant.EdgeType;
import cn.com.mirror.project.unit.element.Class;
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
@NodeEntity(label = "class")
public class ClassNode extends BaseNode {
    private static final long serialVersionUID = 1L;

    @Property(name = "class name")
    private String name;

    @Property(name = "class qualified name")
    private String qualifiedName;

    @Relationship(type = EdgeType.TYPE.CLS_TO_CLS)
    private ClassNode toClass;

    public ClassNode(Integer startLineNum,
                     String targetPath,
                     Integer endLineNum,
                     String content,
                     String packageName,
                     String name,
                     String qualifiedName) {

        super(startLineNum, targetPath, endLineNum, content, packageName);

        this.name = name;
        this.qualifiedName = qualifiedName;
    }

    public static final ClassNode instance(Class cls) {
        Asserts.notNull(cls, "Class element can not be null.");

        return new ClassNode(cls.getStartLineNum(),
                cls.getTargetPath(),
                cls.getEndLineNum(),
                cls.getContent(),
                cls.getPackageName(),
                cls.getName(),
                cls.getQualifiedName());
    }
}
