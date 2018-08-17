package cn.com.mirror.repository.neo4j.storage;

import cn.com.mirror.repository.neo4j.config.Neo4jSessionFactory;
import cn.com.mirror.repository.neo4j.node.BaseNode;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.transaction.Transaction;

/**
 * @author piggy
 * @description
 * @date 18-8-16
 */
public class GraphEngine {
    private Session session;

    public GraphEngine() {
        this(new Neo4jSessionFactory().newSession());
    }

    public GraphEngine(Session session) {
        this.session = session;
    }

    public boolean write(BaseNode baseNode) {
        session.save(baseNode);
        return true;
    }

    public void read() {

    }
}
