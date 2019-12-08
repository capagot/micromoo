# micromoo

A microservice-based MOO created for educational purposes.

**MySQL container Setup**

1. Create the Dockerfile
2. ```docker build -t capagot/micromoo_db_server .```
3. ```docker run --name micromoo_db_container -d -p 9000:3306 capagot/micromoo_db_server```

