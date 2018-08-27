/**
 *
 */
package cn.com.mirror.repository.neo4j.node;

import cn.com.mirror.constant.EdgeType;
import cn.com.mirror.constant.ElementTypeEnum;
import cn.com.mirror.project.unit.element.Class;
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
@NodeEntity(label = "class")
public class ClassNode extends BaseNode {
    private static final long serialVersionUID = 1L;

    @Property(name = "class name")
    private String name;

    @Property(name = "class qualified name")
    private String qualifiedName;

    @Relationship(type = EdgeType.TYPE.CLS_TO_CLS, direction = Relationship.INCOMING)
    private Set<ClassNode> classNodes;

    @Relationship(type = EdgeType.TYPE.MTD_TO_CLS, direction = Relationship.INCOMING)
    private Set<MethodNode> methodNodes;

    public ClassNode(Integer startLineNum,
                     String targetPath,
                     Integer endLineNum,
                     String content,
                     String packageName,
                     String name,
                     String qualifiedName) {

        super(startLineNum, targetPath, endLineNum, content, packageName, ElementTypeEnum.CLASS);

        this.name = name;
        this.qualifiedName = qualifiedName;
        this.classNodes = new HashSet<>();
        this.methodNodes = new HashSet<>();
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

    public void touchClassNode(ClassNode classNode) {
        this.classNodes.add(classNode);
    }

    public void touchMethodNode(MethodNode methodNode) {
        this.methodNodes.add(methodNode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ClassNode classNode = (ClassNode) o;
        return Objects.equals(name, classNode.name) &&
                Objects.equals(qualifiedName, classNode.qualifiedName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), name, qualifiedName);
    }
}
