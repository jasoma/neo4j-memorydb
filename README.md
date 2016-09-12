# neo4j-memorydb

A repackaging of the in-memory database classes from Neo4j. This avoids the need to depend on the test 
artifacts from the Neo4j project. Usage and examples from the main project can be found here: 
http://neo4j.com/docs/stable/tutorials-java-unit-testing.html

## Gotchas

Although not unique to the in-memory database two errors are common if using the in-memory database for testing but
a remote server in production.

1. `org.neo4j.kernel.impl.util.UnsatisfiedDependencyException: No dependency satisfies type class org.neo4j.kernel.api.index.SchemaIndexProvider`
2. `java.lang.UnsupportedOperationException: No query engine installed.`

Both of these are due to neo4j looking up components at runtime. Both can be solved by adding additional dependencies:

```gradle
dependencies {
    // (...)
    
    // adds an index provider for error [1]
    testCompile group: 'org.neo4j', name: 'neo4j-lucene-index', version: neoVersion
    // adds a query engine for error [2]
    testCompile group: 'org.neo4j', name: 'neo4j-cypher', version: neoVersion
    
    // (...)
}
```

