package cn.com.mirror.repository.neo4j.node;

import cn.com.mirror.constant.EdgeType;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

import java.io.Serializable;

/**
 * @author piggy
 * @description
 * @date 18-8-9
 */
@Data
@NodeEntity(label = "a node in target which contains some basic information")
public class BaseNode implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "start line num")
    private Integer startLineNum;

    @Property(name = "target path")
    private String targetPath;

    @Relationship(type = EdgeType.EDGE_TYPE.NODE_TO_NODE_CTRL_EDGE)
    private BaseNode ctrlDepNode;

    BaseNode(){}

    public BaseNode(Integer startLineNum,
                    String targetPath) {

        this.startLineNum = startLineNum;
        this.targetPath = targetPath;
    }

}
