package cn.com.mirror.repository.graph.neo4j.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.v1.*;

import static org.neo4j.driver.v1.Values.parameters;

/**
 * @author piggy
 * @description
 * @date 18-8-8
 */
@Slf4j
@Data
public class Neo4jDriver implements AutoCloseable {
    private final Driver driver;

    public Neo4jDriver() {
        this(Neo4jProperty.newInstance());
    }

    public Neo4jDriver(Neo4jProperty neo4jProperty) {
        driver = GraphDatabase.driver(neo4jProperty.getServAddr(),
                AuthTokens.basic(neo4jProperty.getUsername(), neo4jProperty.getPassword()));
    }

    @Override
    public void close() {
        driver.close();
    }

    public void example(final String message) {

        try (Session session = driver.session()) {
            String greeting = session.writeTransaction(new TransactionWork<String>() {
                @Override
                public String execute(Transaction tx) {
                    StatementResult result = tx.run(
                            "CREATE (a:Greeting) " +
                                    "SET a.message = $message " +
                                    "RETURN a.message + ', from node ' + id(a)",
                            parameters("message", message));
                    return result.single().get(0).asString();
                }
            });

            log.info(greeting);
        }

    }
}
