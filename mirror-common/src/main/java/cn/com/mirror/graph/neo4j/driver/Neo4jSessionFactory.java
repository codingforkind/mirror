package cn.com.mirror.graph.neo4j.driver;

import cn.com.mirror.graph.neo4j.config.Neo4jProperty;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

/**
 * @author piggy
 * @description
 * @date 18-8-8
 */
public class Neo4jSessionFactory {
    private final SessionFactory sessionFactory;
    private final Neo4jSessionFactory neo4jSessionFactory = new Neo4jSessionFactory(Neo4jProperty.newInstance());

    private Neo4jSessionFactory(Neo4jProperty neo4jProperty) {
        Configuration configuration = new Configuration.Builder()
                .uri(neo4jProperty.getServAddr())
                .credentials(neo4jProperty.getUsername(), neo4jProperty.getPassword())
                .build();

        this.sessionFactory = new SessionFactory(configuration, "com.mycompany.app.domainclasses");
    }

    public Neo4jSessionFactory getFactory() {
        return neo4jSessionFactory;
    }

    public Session newSession() {
        return sessionFactory.openSession();
    }


}
