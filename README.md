# MicroMOO

A microservice-based MOO in its very early stages of development. The project was created for educational purposes.

## MicroMOO Current Structure

Currently the system is composed by the following microservices:

* Database service;
* Web service;
* User Management service.

The Database service is implemented by a container containing a [MySQL](https://www.mysql.com) database running on Alpine Linux.

The Web service is composed by a Nginx webserver running on Alpine Linux. The service currently hosts a couple of PHP webpages that implement the temporary interface with the User Management service.

The User Management service is composed by a Java application running on top of [Alpine](https://alpinelinux.org) Linux. The communication with the service is made through a [RESTful](https://en.wikipedia.org/wiki/Representational_state_transfer) interface implemented with [Spark](http://sparkjava.com).

Each service is hosted in a separate [Docker](https://www.docker.com) container. There is actually a fourth container in charge of the [Adminer](https://www.adminer.org), a database web interface system, but it is not part of Micromoo itself and serves only as a debugging tool.

The following image illustrates how the containers are organized inside the Docker Network and how the containers' ports map to the host machine (localhost) ports:

![Docker Network](https://github.com/capagot/micromoo/blob/junit_users_service/misc/docker_scheme.png)

## Compiling and setting up Micromoo

Before compiling and installing Micromoo, you must have docker and Java installed. Once you have everything properly installed, at the root of the project directory tree issue the following commands:

1. ```$ mvn compile```
2. ```$ mvn package -Dmaven.test.skip=true``` (to skip the JUnit tests)
3. ```$ docker-compose up -d --build```

## Accessing Micromoo

To access the Micromoo user management module, just open the following URL on a web browser:

```http://localhost:9000/users_admin.html```

## Shutting Down

1. ```$ docker-compose down```

## TODOs

* Improve the web interface.
* Implement an API gateway.
* Implement a token based authentication system (*e.g.* JWT, OAuth, ...).
* Include data persistence.
* And lots and lots of other things...

## References

* User management service and tests based on:
    * https://www.mscharhag.com/java/building-rest-api-with-spark

* Multimodule Maven project based on:
    * https://books.sonatype.com/mvnex-book/reference/multimodule.html
