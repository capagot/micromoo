package org.micromoo.account;

import static spark.Spark.*;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import org.micromoo.db.MySQL;

public class UserManager {
    public static void main(String[] str) {
        port(4567); // 4567 is the default Spark port

        get("/hello", (req, res)->"Hello! The Users service is alive!");

        post("/users", (request, response) -> {
            response.type("application/json");
            final User user = new Gson().fromJson(request.body(), User.class);

            final String insertNewUser = "INSERT INTO users (user_name, user_password, user_type)\r\n" +
                                         "VALUES ('" + user.getName() + "', '" + user.getPassword() + "', '" + user.getType() + "');";

            final MySQL db = new MySQL("jdbc:mysql://localhost:3306/micromoo_db", "root", "root");
            db.insert(insertNewUser);
            db.getConnection().close();

           return new Gson().toJson(new RESTResponse(ResponseStatus.SUCCESS));
        });

        get("/hello/:name", (req,res)->{
            final MySQL db = new MySQL("jdbc:mysql://localhost:3306/sql_store", "root", "root");

            final String selectCustomerData = "SELECT customer_id,\r\n" +
                                        "       first_name,\r\n" +
                                        "       last_name,\r\n" +
                                        "       birth_date,\r\n" +
                                        "       city,\r\n" +
                                        "       points\r\n" +
                                        "FROM customers\r\n" +
                                        "ORDER BY first_name ASC;";

            final Statement statement = db.getConnection().createStatement();
            final ResultSet rs = statement.executeQuery(selectCustomerData);

            String s = new String();

            while(rs.next()) {
                s = s + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3);
            }

            db.getConnection().close();

            return "Hellouuuuu, "+ req.params(":name") + s;
        });
    }
}
