package cn.com.mirror.repository.neo4j.node;

import cn.com.mirror.constant.EdgeType;
import lombok.Getter;
import org.neo4j.ogm.annotation.*;

import java.io.Serializable;

/**
 * @author piggy
 * @description
 * @date 18-8-9
 */
@Getter
@NodeEntity(label = "a node in target which contains some basic information")
public class BaseNode implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "start line num")
    private Integer startLineNum;

    @Property(name = "target path")
    private String targetPath;

    @Property(name = "end line num")
    private Integer endLineNum;

    @Property(name = "details in this line")
    private String content;

    @Property(name = "package name")
    private String packageName;

    @Relationship(type = EdgeType.EDGE_TYPE.NODE_TO_NODE_CTRL_EDGE)
    private BaseNode ctrlDepNode;

    public BaseNode(Integer startLineNum,
                    String targetPath,
                    Integer endLineNum,
                    String content,
                    String packageName) {

        this.startLineNum = startLineNum;
        this.targetPath = targetPath;
        this.endLineNum = endLineNum;
        this.content = content;
        this.packageName = packageName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BaseNode getCtrlDepNode() {
        return ctrlDepNode;
    }

    public void setCtrlDepNode(BaseNode ctrlDepNode) {
        this.ctrlDepNode = ctrlDepNode;
    }
}
