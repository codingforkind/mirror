package cn.com.mirror.repository.neo4j.node;

import cn.com.mirror.constant.EdgeType;
import cn.com.mirror.constant.ElementTypeEnum;
import cn.com.mirror.project.unit.element.Base;
import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author piggy
 * @description
 * @date 18-8-9
 */
@Data
public class BaseNode implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "start line num")
    private Integer startLineNum;

    @Property(name = "end line num")
    private Integer endLineNum;

    @Property(name = "target path")
    private String targetPath;

    @Property(name = "details")
    private String content;

    @Property(name = "name")
    private String name;

    @Property(name = "qualified name")
    private String qualifiedName;

    @Property(name = "package name")
    private String packageName;

    private ElementTypeEnum elementTypeEnum;

    @Relationship(type = EdgeType.TYPE.CTRL_EDGE, direction = Relationship.INCOMING)
    private BaseNode ctrlDepNode;

    BaseNode(Integer startLineNum,
             String targetPath,
             Integer endLineNum,
             String content,
             String packageName,
             ElementTypeEnum elementTypeEnum) {

        this.startLineNum = startLineNum;
        this.targetPath = targetPath;
        this.endLineNum = endLineNum;
//        this.content = content; // TODO xyz for test
        this.content = null;
        this.packageName = packageName;
        this.elementTypeEnum = elementTypeEnum;
    }

    public static final BaseNode instance(Base base) {

        switch (base.getElementTypeEnum()) {
            case CLASS: {
                return new ClassNode(base.getStartLineNum(),
                        base.getTargetPath(),
                        base.getEndLineNum(),
                        base.getContent(),
                        base.getPackageName(),
                        base.getElementTypeEnum());
            }

            case STATEMENT: {
                return new StatementNode(base.getStartLineNum(),
                        base.getTargetPath(),
                        base.getEndLineNum(),
                        base.getContent(),
                        base.getPackageName(),
                        base.getElementTypeEnum());
            }

            case METHOD: {
                return new MethodNode(base.getStartLineNum(),
                        base.getTargetPath(),
                        base.getEndLineNum(),
                        base.getContent(),
                        base.getPackageName(),
                        base.getElementTypeEnum());
            }

            case ROOT: {
                return new RootNode(base.getStartLineNum(),
                        base.getTargetPath(),
                        base.getEndLineNum(),
                        base.getContent(),
                        base.getPackageName(),
                        base.getElementTypeEnum());
            }

            default: {
                return new BaseNode(base.getStartLineNum(),
                        base.getTargetPath(),
                        base.getEndLineNum(),
                        base.getContent(),
                        base.getPackageName(),
                        base.getElementTypeEnum());
            }
        }

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseNode baseNode = (BaseNode) o;
        return Objects.equals(startLineNum, baseNode.startLineNum) &&
                Objects.equals(endLineNum, baseNode.endLineNum) &&
                Objects.equals(targetPath, baseNode.targetPath) &&
                Objects.equals(content, baseNode.content) &&
                Objects.equals(packageName, baseNode.packageName) &&
                elementTypeEnum == baseNode.elementTypeEnum;
    }

    @Override
    public int hashCode() {

        return Objects.hash(startLineNum, endLineNum, targetPath, content, packageName, elementTypeEnum);
    }
}
