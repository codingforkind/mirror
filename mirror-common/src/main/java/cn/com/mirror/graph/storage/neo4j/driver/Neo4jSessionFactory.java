package cn.com.mirror.graph.storage.neo4j.driver;

import org.neo4j.driver.internal.SessionFactory;

/**
 * @author piggy
 * @description
 * @date 18-8-8
 */
public class Neo4jSessionFactory {
    private final SessionFactory sessionFactory;

    public Neo4jSessionFactory() {
//        Configuration configuration = new Configuration.Builder()
//                .uri("bolt://localhost")
//                .credentials("neo4j", "passwword")
//                .build();
//        SessionFactory sessionFactory = new SessionFactory(configuration, "com.mycompany.app.domainclasses");
        this.sessionFactory = null;
    }
}
