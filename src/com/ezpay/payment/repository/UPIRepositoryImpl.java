/**
 * Module Name: UPIRepository
 * 
 * Description: This module serves as the repository for storing and managing UPI details within the payment system.
 * It interfaces with the database to retrieve and update UPI-related information, such as user details and account balances.
 * The module contains methods to find a user by their UPI ID and to update the user's account information after a transaction.
 * 
 * Author:
 * Agneesh Dasgupta
 * 
 * Date: August 23, 2024
 */

package com.ezpay.payment.repository;

import com.ezpay.payment.model.UPI;
import com.ezpay.payment.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UPIRepositoryImpl implements UPIRepository {
    private Connection connection;

    // Constructor to initialize the repository with a database connection
    public UPIRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Method to find a user by their UPI ID.
     * 
     * @param upiId The UPI ID of the user.
     * @return A UPI object containing user details if found, otherwise null.
     */
    public UPI findUserByUpiId(String upiId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_USERID)) {
            preparedStatement.setString(1, upiId);  // Set the UPI ID parameter in the query
            ResultSet resultSet = preparedStatement.executeQuery();  // Execute the query
            if (resultSet.next()) {
                String custName = resultSet.getString("cust_name");  // Retrieve customer name
                double balance = resultSet.getDouble("balance");  // Retrieve balance
                long mobileNumber = resultSet.getLong("mobile_number");  // Retrieve mobile number
                String email = resultSet.getString("email");  // Retrieve email
                return new UPI(custName, upiId, balance, mobileNumber, email);  // Return a UPI object with the retrieved details
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Print stack trace if an SQL exception occurs
        }
        return null;  // Return null if the user is not found
    }

    /**
     * Method to update a user's details in the database.
     * 
     * @param upi A UPI object containing the updated user details.
     */
    public void updateUser(UPI upi) {
        try (Connection conn = DBConnection.getConnection()) {  // Get a new database connection
            PreparedStatement preparedStatement = conn.prepareStatement(QUERY_UPDATE);
            preparedStatement.setDouble(1, upi.getBalance());  // Set the updated balance
            preparedStatement.setString(2, upi.getUpiId());  // Set the UPI ID
            preparedStatement.executeUpdate();  // Execute the update query
        } catch (SQLException e) {
            e.printStackTrace();  // Print stack trace if an SQL exception occurs
        }
    }
}
