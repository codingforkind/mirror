package cn.com.mirror.graph;

import cn.com.mirror.repository.neo4j.config.Neo4jProperty;
import cn.com.mirror.utils.PropertyUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author piggy
 * @description
 * @date 18-8-8
 */
public class Neo4jTests {
    @Test
    public void testMappingAnnotation() throws IOException, IllegalAccessException {
        Properties props = new Properties();
        InputStream stream = Neo4jTests.class.getResourceAsStream("/neo4j.properties");
        props.load(stream);
        Neo4jProperty neo4jProperty = new Neo4jProperty();
        PropertyUtils.mappingProperties(props, neo4jProperty);
        System.out.println(neo4jProperty.getServAddr());
    }

}
