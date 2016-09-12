package com.github.jasoma.neo4j.memorydb;

import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.test.TestGraphDatabaseFactory;

import static org.junit.Assert.assertNotNull;

public class InMemoryDatabaseTest {

    @Test
    public void testInMemoryCreation() {
        GraphDatabaseService db = new TestGraphDatabaseFactory().newImpermanentDatabase();
        try (Transaction tx = db.beginTx()) {
            db.execute("CREATE (s:Test)-[r:Test]->(e:Test)");
            Result result = db.execute("MATCH (s:Test)-[r:Test]->(e:Test) RETURN s, r, e");

            result.stream().forEach(row -> {
                assertNotNull(row.get("s"));
                assertNotNull(row.get("r"));
                assertNotNull(row.get("e"));
            });

            result.close();
        }
    }

}
