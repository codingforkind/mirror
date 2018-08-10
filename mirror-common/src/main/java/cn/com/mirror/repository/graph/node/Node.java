package cn.com.mirror.repository.graph.node;

import cn.com.mirror.constant.EdgeTypeEnum;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

import java.io.Serializable;

/**
 * @author piggy
 * @description
 * @date 18-8-9
 */
@Data
@NodeEntity(label = "a line of code in target")
public class Node implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "line num")
    private Integer lineNum;

    @Property(name = "target path")
    private String targetPath;

    @Property(name = "node type")
    private String nodeType;

    @Relationship(type = EdgeTypeEnum.EdgeType.CONTROL_EDGE)
    private Node controlDependentOnNode;

}
