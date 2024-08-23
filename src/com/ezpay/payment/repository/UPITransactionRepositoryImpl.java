/**
 * Module Name: UPITransactionRepository
 * 
 * Description: This module is responsible for managing UPI transactions within the payment system.
 * It provides methods to save new transactions and retrieve transaction history by UPI ID.
 * The module interacts with the database to store and retrieve transaction details such as sender and receiver UPI IDs,
 * transaction amount, date, note, and status.
 * 
 * Author:
 * Agneesh Dasgupta
 * 
 * Date: August 23, 2024
 */

package com.ezpay.payment.repository;

import com.ezpay.payment.model.UPITransaction;
import com.ezpay.payment.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UPITransactionRepositoryImpl implements UPITransactionRepository {
    private Connection connection;

    // Constructor to initialize the repository with a database connection
    public UPITransactionRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Method to save a UPI transaction to the database.
     * 
     * @param transaction The UPITransaction object containing the transaction details to be saved.
     */
    public void saveTransaction(UPITransaction transaction) {
        try (Connection conn = DBConnection.getConnection()) {  // Get a new database connection
            PreparedStatement preparedStatement = conn.prepareStatement(QUERY_INSERT_UPITRANSACTIONS);
            preparedStatement.setString(1, transaction.getSenderUpiId());  // Set the sender UPI ID
            preparedStatement.setString(2, transaction.getReceiverUpiId());  // Set the receiver UPI ID
            preparedStatement.setDouble(3, transaction.getAmount());  // Set the transaction amount
            preparedStatement.setDate(4, new java.sql.Date(transaction.getDate().getTime()));  // Set the transaction date
            preparedStatement.setString(5, transaction.getNote());  // Set the transaction note
            preparedStatement.setString(6, transaction.getStatus());  // Set the transaction status
            preparedStatement.executeUpdate();  // Execute the insert query
        } catch (SQLException e) {
            e.printStackTrace();  // Print stack trace if an SQL exception occurs
        }
    }

    /**
     * Method to retrieve the list of transactions for a specific UPI ID.
     * 
     * @param upiId The UPI ID for which the transaction history is to be retrieved.
     * @return A list of UPITransaction objects containing the transaction details.
     */
    public List<UPITransaction> findTransactionsByUpiId(String upiId) {
        List<UPITransaction> transactions = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_FIND_UPITRANSACTIONS)) {
            preparedStatement.setString(1, upiId);  // Set the UPI ID as the sender
            preparedStatement.setString(2, upiId);  // Set the UPI ID as the receiver
            ResultSet resultSet = preparedStatement.executeQuery();  // Execute the query to retrieve transactions

            while (resultSet.next()) {
                String senderUpiId = resultSet.getString("sender_upi_id");  // Retrieve sender UPI ID
                String receiverUpiId = resultSet.getString("receiver_upi_id");  // Retrieve receiver UPI ID
                double amount = resultSet.getDouble("amount");  // Retrieve transaction amount
                Date transactionDate = resultSet.getDate("transaction_date");  // Retrieve transaction date
                String note = resultSet.getString("note");  // Retrieve transaction note
                String status = resultSet.getString("status");  // Retrieve transaction status

                // Create a UPITransaction object and add it to the list
                UPITransaction transaction = new UPITransaction(senderUpiId, receiverUpiId, amount, transactionDate, note, status);
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Print stack trace if an SQL exception occurs
        }
        return transactions;  // Return the list of transactions
    }
}
