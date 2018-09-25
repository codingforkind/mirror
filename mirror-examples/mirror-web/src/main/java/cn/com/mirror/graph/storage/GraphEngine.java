package cn.com.mirror.graph.storage;

import cn.com.mirror.graph.node.BaseNode;
import cn.com.mirror.graph.config.Neo4jSessionFactory;
import org.neo4j.ogm.session.Session;

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
        if (null != baseNode) {
//            session.loadAll(BaseNode.class);
            session.save(baseNode);
        }
        return true;
    }

    public void read() {

    }
}
