package cn.com.mirror.repository.neo4j.node;

import cn.com.mirror.constant.EdgeType;
import cn.com.mirror.constant.NodeTypeEnum;
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

    //    @Property(name = "details in this line")
    private String content;

    @Property(name = "package name")
    private String packageName;

    private NodeTypeEnum nodeType;

    @Relationship(type = EdgeType.TYPE.CTRL_EDGE, direction = Relationship.INCOMING)
    private BaseNode ctrlDepNode;

    public BaseNode(Integer startLineNum,
                    String targetPath,
                    Integer endLineNum,
                    String content,
                    String packageName,
                    NodeTypeEnum nodeType) {

        this.startLineNum = startLineNum;
        this.targetPath = targetPath;
        this.endLineNum = endLineNum;
        this.content = content;
        this.packageName = packageName;
        this.nodeType = nodeType;
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
                nodeType == baseNode.nodeType &&
                Objects.equals(ctrlDepNode, baseNode.ctrlDepNode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(startLineNum, endLineNum, targetPath, content, packageName, nodeType, ctrlDepNode);
    }
}
