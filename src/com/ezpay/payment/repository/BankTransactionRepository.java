/**
 * Module Name: BankTransactionRepository
 *
 * Description: This module  provides methods for saving bank transactions and retrieving transaction history based on account numbers. 
 * The class interacts with the database using JDBC to execute SQL queries and handle the underlying connection to the database.
 * 
 * Author:
 * Hasini Sai Ramya
 * 
 * Date: August 20, 2024
 * 
 */


package com.ezpay.payment.repository;

import com.ezpay.payment.model.BankTransaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BankTransactionRepository {
    private Connection connection;

    public BankTransactionRepository(Connection connection) {
        this.connection = connection; // Store the connection passed to the constructor
    }

    // Save BankTransaction
    public void saveTransaction(BankTransaction transaction) {
        String query = "INSERT INTO bank_transactions (sender_account_number, ifsc_code, receiver_account_number, amount, transaction_date, note, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) { // Use the stored connection
            statement.setString(1, transaction.getSenderAccountNumber());
            statement.setString(2, transaction.getIfscCode());
            statement.setString(3, transaction.getReceiverAccountNumber());
            statement.setDouble(4, transaction.getAmount());
            statement.setDate(5, new java.sql.Date(transaction.getDate().getTime())); // Convert java.util.Date to java.sql.Date
            statement.setString(6, transaction.getNote());
            statement.setString(7, transaction.getStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Find transactions by account number
    public List<BankTransaction> findTransactionsByAccountNumber(String accountNumber) {
        List<BankTransaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM bank_transactions WHERE sender_account_number = ? OR receiver_account_number = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, accountNumber);
            statement.setString(2, accountNumber);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                String senderAccount = result.getString("sender_account_number");
                String ifscCode = result.getString("ifsc_code");
                String receiverAccount = result.getString("receiver_account_number");
                double amount = result.getDouble("amount");
                Date transactionDate = result.getDate("transaction_date");
                String note = result.getString("note");
                String status = result.getString("status");

                BankTransaction transaction = new BankTransaction(senderAccount, ifscCode, receiverAccount, amount, transactionDate, note, status);
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
