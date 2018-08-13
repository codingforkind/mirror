package cn.com.mirror.graph;

import cn.com.mirror.repository.neo4j.config.Neo4jDriver;
import org.junit.Test;

/**
 * @author piggy
 * @description
 * @date 18-8-8
 */
public class Neo4jDriverTests {
    @Test
    public void testDriver() {
        Neo4jDriver neo4jDriver = new Neo4jDriver();
        neo4jDriver.example("hello");
        neo4jDriver.close();
    }
}
