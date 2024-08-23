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

public class UPITransactionRepository {
    private Connection connection;

    // Constructor to initialize the repository with a database connection
    public UPITransactionRepository(Connection connection) {
        this.connection = connection;
    }

    /**
     * Method to save a UPI transaction to the database.
     * 
     * @param transaction The UPITransaction object containing the transaction details to be saved.
     */
    public void saveTransaction(UPITransaction transaction) {
        try (Connection conn = DBConnection.getConnection()) {  // Get a new database connection
            String query = "INSERT INTO upi_transactions (sender_upi_id, receiver_upi_id, amount, transaction_date, note, status) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, transaction.getSenderUpiId());  // Set the sender UPI ID
            statement.setString(2, transaction.getReceiverUpiId());  // Set the receiver UPI ID
            statement.setDouble(3, transaction.getAmount());  // Set the transaction amount
            statement.setDate(4, new java.sql.Date(transaction.getDate().getTime()));  // Set the transaction date
            statement.setString(5, transaction.getNote());  // Set the transaction note
            statement.setString(6, transaction.getStatus());  // Set the transaction status
            statement.executeUpdate();  // Execute the insert query
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
        String query = "SELECT * FROM upi_transactions WHERE sender_upi_id = ? OR receiver_upi_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, upiId);  // Set the UPI ID as the sender
            statement.setString(2, upiId);  // Set the UPI ID as the receiver
            ResultSet result = statement.executeQuery();  // Execute the query to retrieve transactions

            while (result.next()) {
                String senderUpiId = result.getString("sender_upi_id");  // Retrieve sender UPI ID
                String receiverUpiId = result.getString("receiver_upi_id");  // Retrieve receiver UPI ID
                double amount = result.getDouble("amount");  // Retrieve transaction amount
                Date transactionDate = result.getDate("transaction_date");  // Retrieve transaction date
                String note = result.getString("note");  // Retrieve transaction note
                String status = result.getString("status");  // Retrieve transaction status

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
