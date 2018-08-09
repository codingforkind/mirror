package cn.com.mirror.graph.neo4j.node;

import cn.com.mirror.graph.neo4j.edge.EdgeTypeEnum;
import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.io.Serializable;

/**
 * @author piggy
 * @description
 * @date 18-8-9
 */
@Data
@NodeEntity(label = "a line of code in target")
public class Node implements Serializable {
    private Long id;

    private Integer lineNum;

    private String targetPath;

    private String nodeType;

    @Relationship(type = EdgeTypeEnum.EdgeType.CONTROL_EDGE)
    private Node controlDependentOnNode;

}
