# Backend Serivce

## Setup
Copy JDBC Driver into Glassfish Library
```shell
cp postgresql-42.6.0.jar $GLASSFISH_HOME/glassfish/domains/domain2/lib
```

Add JDBC Connection to Glassfish
```shell
asadmin --port 4852 create-jdbc-connection-pool --datasourceclassname org.postgresql.ds.PGSimpleDataSource --restype \
  javax.sql.DataSource --property portNumber=5432:password=admin:user=admin:serverName=localhost:databaseName=samples samples
asadmin --port 4852 create-jdbc-resource --connectionpoolid samples jdbc/samples
```
Or by the Admin GUI
![](.README_images/534f316b.png)
![](.README_images/461e3372.png)

Add Persistence unit
```xml
<persistence-unit name="default">
  <jta-data-source>jdbc/samples</jta-data-source>
  <properties>
    <property name="jakarta.persistence.jdbc.driver" value="org.postgresql.Driver"/>
    <property name="jakarta.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/samples"/>
    <property name="jakarta.persistence.jdbc.user" value="admin"/>
    <property name="jakarta.persistence.jdbc.password" value="admin"/>
    <property name="jakarta.persistence.schema-generation.database.action" value="create"/>
  </properties>
</persistence-unit>
```

Add Bookstore to Glassfish
```shell
asadmin --port 4852 create-jdbc-connection-pool --datasourceclassname org.postgresql.ds.PGSimpleDataSource --restype \
  javax.sql.DataSource --property portNumber=5432:password=admin:user=admin:serverName=localhost:databaseName=bookstore bookstore
asadmin --port 4852 create-jdbc-resource --connectionpoolid bookstore jdbc/bookstore
```

Add this JDBC connection to the Persistence.xml
```xml
<persistence-unit name="bookstore">
  <jta-data-source>jdbc/bookstore</jta-data-source>
  <properties>
    <property name="jakarta.persistence.jdbc.driver" value="org.postgresql.Driver"/>
    <property name="jakarta.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/bookstore"/>
    <property name="jakarta.persistence.jdbc.user" value="admin"/>
    <property name="jakarta.persistence.jdbc.password" value="admin"/>
    <property name="jakarta.persistence.schema-generation.database.action" value="create"/>
  </properties>
</persistence-unit>
```
