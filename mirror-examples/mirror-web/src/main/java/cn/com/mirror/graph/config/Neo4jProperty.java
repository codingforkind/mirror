package cn.com.mirror.graph.config;

import cn.com.mirror.annotation.Bind;
import cn.com.mirror.utils.PropertyUtils;
import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author piggy
 * @description
 * @date 18-8-8
 */
@Data
public class Neo4jProperty {

    private static Neo4jProperty instance = null;

    @Bind("graph.server")
    private String servAddr;

    @Bind("graph.username")
    private String username;

    @Bind("graph.password")
    private String password;

    /**
     * Get a Neo4jProperty instance.
     */
    public final static Neo4jProperty newInstance() {

        if (null == instance) {
            synchronized (Neo4jProperty.class) {
                if (null == instance) {
                    Properties props = new Properties();
                    InputStream stream = Neo4jProperty.class.getResourceAsStream("/neo4j.properties");
                    try {
                        props.load(stream);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    instance = new Neo4jProperty();
                    try {
                        PropertyUtils.mappingProperties(props, instance);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return instance;
    }

}
