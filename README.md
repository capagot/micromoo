# micromoo

A microservice-based MOO, in early stages of development, created for educational purposes.

**Current structure**

Currently the system is composed by the following three microservices:

* Database service.
* Web service.
* User management service.

The Database service is composed by a container containing a MySQL database running on an Alpine Linux.
The Web service is composed by a Nginx websever running on a Alpine Linux. The service currently hosts a couple of PHP webpages that implement the temporary interface with the User Management service.
The User Management service is composed by a Java application running on top of an Alpine Linux. The communication with the service is made through a RESTful interface implemented with Spark.

**Compiling and setting up Micromoo**

1. ```$ mvn compile```
2. ```$ mvn package -Dmaven.test.skip=true``` (to skip the JUnit tests)
3. ```$ docker-compose up -d --build```

**Accessing Micromoo**

To access Micromoo, just open the following URL on a web browser:

```http://localhost:9000/users_admin.html```

**Shutting Down Micromoo**

1. ```$ docker-compose down```

**TODOs**

* Improve the web interface.
* Implement an API gateway.
* Implement a token based authentication system (*e.g.* JWT, OAuth, ...).
* Include data persistence.

**References**

* User management service and tests based on:
    * https://www.mscharhag.com/java/building-rest-api-with-spark

* Multimodule Maven project based on:
    * https://books.sonatype.com/mvnex-book/reference/multimodule.html
