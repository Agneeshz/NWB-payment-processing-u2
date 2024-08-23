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

public class BankUserRepositoryImpl implements BankUserRepository {
    private Connection connection;

    public BankUserRepositoryImpl(Connection connection) {
        this.connection = connection; // Store the connection passed to the constructor
    }

    // Find BankUser by Account Number
    public BankUser findUserByAccountNumber(String accountNumber) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_BANK_USER)) { // Use the stored connection
            preparedStatement.setString(1, accountNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String custName = resultSet.getString("cust_name");
                String ifscCode = resultSet.getString("ifsc_code");
                double balance = resultSet.getDouble("balance");
                connection.commit();
                return new BankUser(custName, accountNumber, ifscCode, balance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update BankUser's details
    public void updateUser(BankUser bankUser) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE)) {
            preparedStatement.setDouble(1, bankUser.getBalance());
            preparedStatement.setString(2, bankUser.getAccountNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
