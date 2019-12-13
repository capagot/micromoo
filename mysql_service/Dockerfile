FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD 1234
ENV MYSQL_DATABASE micromoo_db
ENV MYSQL_USER micromoo_user
ENV MYSQL_PASSWORD 123

ADD micromoo_db_script.sql /docker-entrypoint-initdb.d

EXPOSE 3306
