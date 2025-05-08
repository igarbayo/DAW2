package Model.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    /* Singleton instance */
    private static UserDAO instance = null;

    /* Private constructor to prevent instantiation from outside */
    private UserDAO() {
    }

    /* Static method to get the singleton instance */
    public static UserDAO getInstance() {

        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    /**
     *  Checks if a given pair email-password is stored in the database
     *
     *  @param email     the email parameter as string
     *  @param password  the password parameter as plain text
     *  @return true if the user was authenticated correctly, false otherwise
     */
    public boolean authenticate(String email, String password) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.obtainConnection();
            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            return resultSet.next(); // if a record is found, the user is authenticated
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // returns false in case of any SQL exception
        } finally {
            closeResources(resultSet, statement);
        }
    }

    /**
     *  Checks if a given email is already registered in the database
     *
     *  @param email    the email parameter as string
     *  @return true if the email was found in the database, false otherwise
     */
    public boolean isEmailRegistered(String email) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.obtainConnection();
            String query = "SELECT COUNT(*) FROM users WHERE email = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(resultSet, statement);
        }

        return false; // returns false in case of error
    }

    /**
     *  Registers a new user in the database.
     *
     *  @param name         the complete name of the user (not compulsory for the purposes of this program, but a good addition)
     *  @param email        the email of the new user
     *  @param password     the password of the new user
     *  @param cardType     the credit card type of the new user
     *  @param cardNumber   the credit card number of the new user
     *  @return true if the user was successfully registered, false otherwise
     */
    public boolean registerUser(String name, String email, String password, String cardType, String cardNumber) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnection.obtainConnection();

            /* Prepare SQL statement */
            String query = "INSERT INTO users (name, email, password, credit_card_type, credit_card_number) VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, cardType);
            statement.setString(5, cardNumber);

            /* Execute SQL statement */
            int rowsInserted = statement.executeUpdate();

            /* Return true if the user was successfully registered */
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // returns false in case of any SQL exception
        } finally {
            closeResources(null, statement);
        }
    }

    /**
     * Retrieves the username associated with the given email from the database.
     *
     * @param email the email address of the user
     * @return the username associated with the email, or null if no such user exists
     */
    public String getUsernameByEmail(String email) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.obtainConnection();
            String query = "SELECT name FROM users WHERE email = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(resultSet, statement);
        }

        return null; // returns null if no user found or error occurred
    }

    /**
     *  Deallocates the memory associated to the result set and the prepared statement
     *
     *  @param resultSet  the ResultSet to be closed
     *  @param statement  the PreparedStatement to be closed
     */
    private void closeResources(ResultSet resultSet, PreparedStatement statement) {

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        /* No need to close the connection as it implements the Singleton pattern */
    }
}

