package user_manager;

import static spark.Spark.*;
import com.google.gson.Gson;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

final class Database {
    public Database(final String host, final String user, final String password) {
        try {
            connection = DriverManager.getConnection(host, user, password);
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public final Connection getConnection() {
        return connection;
    }

    public final void insert(final String insertString) {
        try {
            connection.createStatement().executeUpdate(insertString);
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public final void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    private final static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    private Connection connection;
}


public class UserManager {
    public static void main(String[] str) {
        port(4567); // 4567 is the default Spark port

        get("/hello", (req, res)->"Hello! The User service is alive!");

        post("/users", (request, response) -> {
            response.type("application/json");
            final User user = new Gson().fromJson(request.body(), User.class);

            final String insertNewUser = "INSERT INTO users (user_name, user_password, user_type)\r\n" +
                                   "VALUES ('" + user.getName() + "', '" + user.getPassword() + "', '" + user.getType() + "');";

            final Database db = new Database("jdbc:mysql://localhost:3306/micromoo_db", "root", "root");
            db.insert(insertNewUser);
            db.getConnection().close();

            return new Gson().toJson(new RESTResponse(ResponseStatus.SUCCESS));
        });


        // get("/hello/:name", (req,res)->{
        //     Database db = new Database("jdbc:mysql://localhost:3306/sql_store", "root", "root");

        //     String selectCustomerData = "SELECT customer_id,\r\n" +
        //                                 "       first_name,\r\n" +
        //                                 "       last_name,\r\n" +
        //                                 "       birth_date,\r\n" +
        //                                 "       city,\r\n" +
        //                                 "       points\r\n" +
        //                                 "FROM customers\r\n" +
        //                                 "ORDER BY first_name ASC;";

        //     Statement statement = db.getConnection().createStatement();
        //     ResultSet rs = statement.executeQuery(selectCustomerData);

        //     String s = new String();

        //     while(rs.next()) {
        //         s = s + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3);
        //     }

        //     db.getConnection().close();

        //     return "Hellouuuuu, "+ req.params(":name") + s;
        // });
    }
}
