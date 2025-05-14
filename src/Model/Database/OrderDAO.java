package Model.Database;

import java.sql.*;

public class OrderDAO {

    /* Singleton instance */
    private static OrderDAO instance = null;

    /* Private constructor to prevent instantiation from outside */
    private OrderDAO() {
    }

    /* Static method to get the singleton instance */
    public static OrderDAO getInstance() {
        if (instance == null) {
            instance = new OrderDAO();
        }
        return instance;
    }

    /**
     * Saves a new order to the database.
     *
     * @param mail        the user email associated with the order
     * @param totalAmount the total amount of the order
     * @return true if the order was successfully saved, false otherwise
     */
    public int saveOrder(String mail, double totalAmount) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.obtainConnection();
            String query = "INSERT INTO orders (email, total_amount) VALUES (?, ?) RETURNING order_id";
            statement = connection.prepareStatement(query);
            statement.setString(1, mail);
            statement.setDouble(2, totalAmount);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("order_id"); // returns the id if everything went well
            } else {
                return -1; // returns -1 if the order id could not be retrieved
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // returns -1 if failed
        } finally {
            closeResources(resultSet, statement);
        }
    }

    /**
     * Retrieves the order date associated with the given order ID from the database.
     *
     * @param orderId the ID of the order
     * @return the date of the order, or null if no such order exists
     */
    public Timestamp getOrderDateById(int orderId) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.obtainConnection();
            String query = "SELECT order_date FROM orders WHERE order_id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, orderId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getTimestamp("order_date");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(resultSet, statement);
        }

        return null; // returns null if no order found or error occurred
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