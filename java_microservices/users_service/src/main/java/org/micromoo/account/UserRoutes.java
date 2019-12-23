package org.micromoo.account;

import static spark.Spark.*;
import spark.Spark;
import java.util.logging.*;

public class UserRoutes {
    public UserRoutes(final UserOperations userOp) {
        Logger LOGGER = Logger.getLogger(UserRoutes.class.getName());

        LOGGER.info("Setting up user routes.");

        port(4567); // 4567 is the default Spark port

        get("/hello", (request, response)->"Hello! The Users service is alive!");

        get("/users", (request, response)->userOp.getAllUsers(), JsonUtil.json());

        get("/users/:user_id", (request, response)->userOp.getUser(request.params(":user_id")), JsonUtil.json());

        post("/users", (request, response)->userOp.createUser(request.queryParams("user_name"),
                                                              request.queryParams("user_password"),
                                                              request.queryParams("user_type")), JsonUtil.json());

        put("/users/:user_id", (request, response)->userOp.updateUser(request.params(":user_id"),
                                                                      request.queryParams("user_name"),
                                                                      request.queryParams("user_password"),
                                                                      request.queryParams("user_type")), JsonUtil.json());

        Spark.awaitInitialization();
    }
}
