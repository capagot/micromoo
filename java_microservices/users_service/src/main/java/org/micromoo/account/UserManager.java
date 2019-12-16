package org.micromoo.account;

import static spark.Spark.*;
import com.google.gson.Gson;
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

            //final MySQL db = new MySQL("jdbc:mysql://localhost:3306/micromoo_db", "root", "root");
            final MySQL db = new MySQL("jdbc:mysql://db-service:3306/micromoo_db", "micromoo_user", "123");

            db.insert(insertNewUser);
            db.getConnection().close();

           return new Gson().toJson(new RESTResponse(ResponseStatus.SUCCESS));
        });

        get("/hello/:name", (req,res)->{
            //final MySQL db = new MySQL("jdbc:mysql://localhost:3306/micromoo_db", "micromoo_user", "123");
            final MySQL db = new MySQL("jdbc:mysql://db-service:3306/micromoo_db", "micromoo_user", "123");

            final String selectUserData = "SELECT user_id, user_name, user_password FROM users;";

            final ResultSet rs = db.select(selectUserData);

            String s = new String();

            while(rs.next()) {
                s = s + rs.getString(1) + " " + rs.getString(2);
            }

            db.getConnection().close();

            return "Hellouuuuu, "+ req.params(":name") + s;
        });
    }
}
