/**
 * Module Name: BankUserRepository
 *
 * Description: This module provides methods for finding a bank user by their account number and updating the user's details, 
 * including their balance. The class interacts with the database using JDBC to execute SQL queries and manage database connections.
 * 
 * Author:
 * Hasini Sai Ramya
 * 
 * Date: August 20, 2024
 * 
 */


package com.ezpay.payment.repository;

import com.ezpay.payment.model.BankUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankUserRepository {
    private Connection connection;

    public BankUserRepository(Connection connection) {
        this.connection = connection; // Store the connection passed to the constructor
    }

    // Find BankUser by Account Number
    public BankUser findUserByAccountNumber(String accountNumber) {
        String query = "SELECT * FROM bank_user WHERE account_number = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) { // Use the stored connection
            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String custName = rs.getString("cust_name");
                String ifscCode = rs.getString("ifsc_code");
                double balance = rs.getDouble("balance");
                return new BankUser(custName, accountNumber, ifscCode, balance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update BankUser's details
    public void updateUser(BankUser bankUser) {
        String query = "UPDATE bank_user SET balance = ? WHERE account_number = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDouble(1, bankUser.getBalance());
            stmt.setString(2, bankUser.getAccountNumber());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
