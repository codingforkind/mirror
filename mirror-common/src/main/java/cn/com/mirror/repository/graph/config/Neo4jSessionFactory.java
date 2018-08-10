package cn.com.mirror.repository.graph.config;

import org.neo4j.ogm.config.ClasspathConfigurationSource;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.config.ConfigurationSource;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

/**
 * @author piggy
 * @description
 * @date 18-8-8
 */
public class Neo4jSessionFactory {
    private final SessionFactory sessionFactory;

    private Neo4jSessionFactory() {
        ConfigurationSource props = new ClasspathConfigurationSource("neo4j.properties");
        Configuration configuration = new Configuration.Builder(props).build();
        this.sessionFactory = new SessionFactory(configuration, "cn.com.mirror.repository.neo4j");
    }

    public Session newSession() {
        return sessionFactory.openSession();
    }

}
