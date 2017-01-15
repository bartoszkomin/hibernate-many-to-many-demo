# hibernate-many-to-many-demo

Hibernate many to many relation demo with spring boot REST API and json never ending recursion protection

This is example project for post on blog [bartoszkomin.blogspot.com][1], please go there to find more informations.

To run project you need:
- maven
- java 8

You don't need to install any database, it works with embeded "in memory" HyperSQL Database. It contains also embeded tomcat server.

To run project follow steps:
- mvn install
- on project folder run: java -jar target/hibernate-many-to-many-demo-1.0.jar
- use your favorite application to test API endpoints (for example: Advanced REST client in chrome, Postman, or simple curl command) for example: GET http://localhost:8080/users

You can use this curl commands to test endpoints:

- To add books

	    curl -i -H "Content-Type: application/json" -X POST -d '{"name": "The Meaning of Relativity"}' http://127.0.0.1:8080/books

- To add users with book:

	    curl -i -H "Content-Type: application/json" -X POST -d '{"name": "Albert Einstein", "books":[{"id":1}]}' http://127.0.0.1:8080/users

- To get the all users:

	    curl -i -H "Content-Type: application/json" http://127.0.0.1:8080/users

- To get the all books:

	    curl -i -H "Content-Type: application/json" http://127.0.0.1:8080/books

- To get single user:

	    curl -i -H "Content-Type: application/json" http://127.0.0.1:8080/users/1

- To get single book:

	    curl -i -H "Content-Type: application/json" http://127.0.0.1:8080/books/1

[1]: http://bartoszkomin.blogspot.com