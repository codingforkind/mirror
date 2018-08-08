package cn.com.mirror.graph.storage.neo4j.config;

import cn.com.mirror.annotation.Bind;
import lombok.Data;

/**
 * @author piggy
 * @description
 * @date 18-8-8
 */
@Data
public class Neo4jProperty {
    @Bind("neo4j.server")
    private String servAddr;
}
