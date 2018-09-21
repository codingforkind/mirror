package cn.com.mirror.project.graph.config;

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

    private SessionFactory sessionFactory;

    public Neo4jSessionFactory() {

        ConfigurationSource props = new ClasspathConfigurationSource("neo4j.properties");
        Configuration configuration = new Configuration.Builder(props).build();

        sessionFactory = new SessionFactory(configuration, "cn.com.mirror.project.graph.node");
    }

    public Session newSession() {
        return sessionFactory.openSession();
    }

}
