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
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) { // Use the stored connection
            preparedStatement.setString(1, transaction.getSenderAccountNumber());
            preparedStatement.setString(2, transaction.getIfscCode());
            preparedStatement.setString(3, transaction.getReceiverAccountNumber());
            preparedStatement.setDouble(4, transaction.getAmount());
            preparedStatement.setDate(5, new java.sql.Date(transaction.getDate().getTime())); // Convert java.util.Date to java.sql.Date
            preparedStatement.setString(6, transaction.getNote());
            preparedStatement.setString(7, transaction.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Find transactions by account number
    public List<BankTransaction> findTransactionsByAccountNumber(String accountNumber) {
        List<BankTransaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM bank_transactions WHERE sender_account_number = ? OR receiver_account_number = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, accountNumber);
            preparedStatement.setString(2, accountNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String senderAccount = resultSet.getString("sender_account_number");
                String ifscCode = resultSet.getString("ifsc_code");
                String receiverAccount = resultSet.getString("receiver_account_number");
                double amount = resultSet.getDouble("amount");
                Date transactionDate = resultSet.getDate("transaction_date");
                String note = resultSet.getString("note");
                String status = resultSet.getString("status");

                BankTransaction transaction = new BankTransaction(senderAccount, ifscCode, receiverAccount, amount, transactionDate, note, status);
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
