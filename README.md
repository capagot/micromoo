# micromoo

A microservice-based MOO created for educational purposes.

**Setting Up the Services**

1. ```$ mvn compile```
2. ```$ mvn package```
3. ```$ docker-compose up -d --build```

**Shutting Down the Services**

1. ```$ docker-compose down```

**TODOs**

* Web interface.
* API gateway.
* Authentication (JWT, OAuth, ...).
* Data persistence.
* Database connection strings in a properties file.

**References**

* User service based on:
    * https://www.mscharhag.com/java/building-rest-api-with-spark

* Multimodule Maven project based on:
    * https://books.sonatype.com/mvnex-book/reference/multimodule.html

