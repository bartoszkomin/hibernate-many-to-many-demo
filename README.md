# hibernate-many-to-many-demo

Hibernate many to many relation demo with spring boot REST API and json never ending recursion protection

This is example project for post on blog  bartoszkomin.blogspot.com, please go there to find more informations.

To run project you need:
- maven
- java 8

You don't need to install any database, it works with embeded "in memory" HyperSQL Database. It contains also embeded tomcat server.

To run project follow steps:
- mvn install
- on project folder run: java -jar target/hibernate-many-to-many-demo-1.0.jar
- use your favorite application to run API endpoints (for example: Advanced REST client in chrome, Postman, or simple curl command) for example: GET http://localhost:8080/users