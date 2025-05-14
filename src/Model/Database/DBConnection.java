package Model.Database;


import java.sql.*;
/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;*/

/**
 *  Utility class to improve the database connection by using the Singleton pattern.
 *  Makes sure that and only one instance of the connection is active at once (alongside the whole application).
 */
public class DBConnection {

    /* Database connection parameters */
    private static Connection connection = null;
    private static final String URL = "jdbc:postgresql://localhost:5432/tienda";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    /* Static block to set up the JDBC driver for PostgreSQL */
    /* This is not required (but still supported) in the latest versions of jdbc: https://jdbc.postgresql.org/documentation/use/ */
    static {
        try {
            /* Registers the drive itself */
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            /* Throws error in case the driver is not found */
            throw new ExceptionInInitializerError();
        }
    }

    /**
     *  Private constructor to prevent the instantiation of the class.
     */
    private DBConnection() {
    }

    /**
     *  Gets a single instance of the database connection.
     *  If the connection is not created or closed, a new one is created.
     *
     *  @return Connection object representing an active connection to the database.
     *  @throws SQLException If the connection could not be established.
     */
    public static Connection obtainConnection() throws SQLException {

        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }

    /**
     *  Closes the current database connection if active.
     */
    public static void closeConnection() {

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                /* Error handling if the connection could not be closed properly */
                e.printStackTrace();
            }
        }
    }
}
