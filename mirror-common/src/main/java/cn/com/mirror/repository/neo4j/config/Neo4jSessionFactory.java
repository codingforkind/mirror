package cn.com.mirror.repository.neo4j.config;

import org.neo4j.ogm.config.ClasspathConfigurationSource;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.config.ConfigurationSource;
import org.neo4j.ogm.driver.Driver;
import org.neo4j.ogm.drivers.bolt.driver.BoltDriver;
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
//        ConfigurationSource props = new ClasspathConfigurationSource("neo4j.properties");
//        Configuration configuration = new Configuration.Builder(props).build();
//        System.out.println(configuration);

        Driver driver = new BoltDriver(new Neo4jDriver().getDriver());
        sessionFactory = new SessionFactory(driver, "cn.com.mirror.repository.neo4j.node");
    }

    public final Session newSession() {
        return sessionFactory.openSession();
    }

}
