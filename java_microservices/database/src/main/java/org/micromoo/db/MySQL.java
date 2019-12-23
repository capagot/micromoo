package org.micromoo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public final class MySQL {
    public MySQL(final String host, final String user, final String password) {
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

    public final void update(final String updateString) {
        insert(updateString);
    }

    public final ResultSet select(final String selectString) {
        try {
            return connection.createStatement().executeQuery(selectString);
        } catch (SQLException e) {
            printSQLException(e);
        }

        return null;
    }

    public final void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    private final static void printSQLException(final SQLException ex) {
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
