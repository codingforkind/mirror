package cn.com.mirror.graph.storage.neo4j.driver;

import cn.com.mirror.graph.storage.neo4j.config.Neo4jProperty;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.SessionFactory;

/**
 * @author piggy
 * @description
 * @date 18-8-8
 */
public class Neo4jSessionFactory {
    private final SessionFactory sessionFactory;

    public Neo4jSessionFactory() {
        this(Neo4jProperty.newInstance());
    }

    public Neo4jSessionFactory(Neo4jProperty neo4jProperty) {
        Configuration configuration = new Configuration.Builder()
                .uri(neo4jProperty.getServAddr())
                .credentials(neo4jProperty.getUsername(), neo4jProperty.getPassword())
                .build();

        this.sessionFactory = new SessionFactory(configuration, "com.mycompany.app.domainclasses");
    }
}
