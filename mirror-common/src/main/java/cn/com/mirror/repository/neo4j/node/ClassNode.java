/**
 *
 */
package cn.com.mirror.repository.neo4j.node;

import lombok.Getter;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * @author Piggy
 * @description
 * @since 2018年4月19日
 */
@Getter
@NodeEntity(label = "a class in target")
public class ClassNode extends BaseNode {
    private static final long serialVersionUID = 1L;

    @Property(name = "class name")
    private String name;

    @Property(name = "class qualified name")
    private String qualifiedName;

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
}
