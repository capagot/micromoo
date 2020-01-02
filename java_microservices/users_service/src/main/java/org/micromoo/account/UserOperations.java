package org.micromoo.account;

import org.micromoo.db.MySQL;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.*;
import java.util.List;
import java.util.ArrayList;

public final class UserOperations {
    public UserOperations () {
        LOGGER = Logger.getLogger(UserOperations.class.getName());
    }

    public final List<User> getAllUsers() throws SQLException {
        LOGGER.info("Getting users list information.");

        final String selectUserData = "SELECT user_id,\r\n" +
                                      "  user_name,\r\n" +
                                      "  user_password,\r\n" +
                                      "  user_type\r\n" +
                                      "FROM users;";

        //final MySQL db = new MySQL("jdbc:mysql://localhost:3306/micromoo_db", "root", "root");
        final MySQL db = new MySQL("jdbc:mysql://db-service:3306/micromoo_db", "micromoo_user", "123");
        final ResultSet rs = db.select(selectUserData);

        ArrayList<User> user_list = new ArrayList<User>();

        try {
            while (rs.next())
                user_list.add(new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.getConnection().close();
        return user_list;
    }

    public final User getUser(final String user_id) throws SQLException {
        LOGGER.info("Getting user information.");

        final String selectUserData = "SELECT user_id,\r\n" +
                                      "  user_name,\r\n" +
                                      "  user_password,\r\n" +
                                      "  user_type\r\n" +
                                      "FROM users\r\n" +
                                      "WHERE user_id = " + user_id + ";";

        //final MySQL db = new MySQL("jdbc:mysql://localhost:3306/micromoo_db", "root", "root");
        final MySQL db = new MySQL("jdbc:mysql://db-service:3306/micromoo_db", "micromoo_user", "123");
        final ResultSet rs = db.select(selectUserData);

        if (rs.next()) {
            final String user_name = rs.getString(2);
            final String user_password = rs.getString(3);
            final String user_type = rs.getString(4);
            db.getConnection().close();
            return new User(user_id, user_name, user_password, user_type);
        } else {
            db.getConnection().close();
            return null;
        }
    }

    public final User createUser(final String user_name, final String user_password, final String user_type)  throws SQLException {
        LOGGER.info("Creating a new user.");

        final String insertUserData = "INSERT INTO users\r\n" +
                                      "  (user_name,\r\n" +
                                      "   user_password,\r\n" +
                                      "   user_type)\r\n" +
                                      "VALUE\r\n" +
                                      "  (\"" + user_name + "\",\r\n" +
                                      "   \"" + user_password + "\",\r\n" +
                                      "   " + user_type + ");\r\n";

        LOGGER.info("INSERTION statatemen: " + insertUserData);

        //final MySQL db = new MySQL("jdbc:mysql://localhost:3306/micromoo_db", "root", "root");
        final MySQL db = new MySQL("jdbc:mysql://db-service:3306/micromoo_db", "micromoo_user", "123");
        db.insert(insertUserData);

        final String selectUserData = "SELECT user_id,\r\n" +
                                      "  user_name,\r\n" +
                                      "  user_password,\r\n" +
                                      "  user_type\r\n" +
                                      "FROM users\r\n" +
                                      "WHERE user_name = \"" + user_name + "\";";

        final ResultSet rs = db.select(selectUserData);

        if (rs.next()) {
            final String rs_user_id = rs.getString(1);
            final String rs_user_name = rs.getString(2);
            final String rs_user_password = rs.getString(3);
            final String rs_user_type = rs.getString(4);
            db.getConnection().close();
            return new User(rs_user_id, rs_user_name, rs_user_password, rs_user_type);
        } else {
            db.getConnection().close();
            return null;
        }
    }

    public final User updateUser(final String user_id, final String user_name, final String user_password, final String user_type)  throws SQLException {
        LOGGER.info("Updating a user.");

        final String updateUserData = "UPDATE users\r\n" +
                                      "SET user_name = \"" + user_name + "\",\r\n" +
                                      "    user_password = \"" + user_password + "\",\r\n" +
                                      "    user_type = " + user_type + "\r\n" +
                                      "WHERE user_id = " + user_id + ";";

        LOGGER.info("UPDATE statatemen: " + updateUserData);

        //final MySQL db = new MySQL("jdbc:mysql://localhost:3306/micromoo_db", "root", "root");
        final MySQL db = new MySQL("jdbc:mysql://db-service:3306/micromoo_db", "micromoo_user", "123");
        db.update(updateUserData);

        final String selectUserData = "SELECT user_id,\r\n" +
                                      "  user_name,\r\n" +
                                      "  user_password,\r\n" +
                                      "  user_type\r\n" +
                                      "FROM users\r\n" +
                                      "WHERE user_id = " + user_id + ";";

        final ResultSet rs = db.select(selectUserData);

        if (rs.next()) {
            final String rs_user_id = rs.getString(1);
            final String rs_user_name = rs.getString(2);
            final String rs_user_password = rs.getString(3);
            final String rs_user_type = rs.getString(4);
            db.getConnection().close();
            return new User(rs_user_id, rs_user_name, rs_user_password, rs_user_type);
        } else {
            db.getConnection().close();
            return null;
        }
    }

    private final Logger LOGGER;
}
