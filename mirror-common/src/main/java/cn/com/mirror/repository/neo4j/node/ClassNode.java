/**
 *
 */
package cn.com.mirror.repository.neo4j.node;

import cn.com.mirror.constant.EdgeType;
import cn.com.mirror.constant.ElementTypeEnum;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Piggy
 * @description
 * @since 2018年4月19日
 */
@Getter
@Setter
@NodeEntity(label = "class")
public class ClassNode extends BaseNode {
    private static final long serialVersionUID = 1L;

    @Relationship(type = EdgeType.TYPE.CLS_TO_CLS, direction = Relationship.INCOMING)
    private Set<ClassNode> classNodes;

    @Relationship(type = EdgeType.TYPE.MTD_TO_CLS, direction = Relationship.INCOMING)
    private Set<MethodNode> methodNodes;

    ClassNode(Integer startLineNum,
              String targetPath,
              Integer endLineNum,
              String content,
              String packageName,
              ElementTypeEnum nodeType) {

        super(startLineNum, targetPath, endLineNum, content, packageName, nodeType);

        this.classNodes = new HashSet<>();
        this.methodNodes = new HashSet<>();
    }

    public void addClassNode(ClassNode classNode) {
        this.classNodes.add(classNode);
    }

    public void addMethodNode(MethodNode methodNode) {
        this.methodNodes.add(methodNode);
    }

}
