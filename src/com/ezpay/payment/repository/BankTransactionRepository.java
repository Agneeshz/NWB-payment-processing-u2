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
        try (PreparedStatement stmt = connection.prepareStatement(query)) { // Use the stored connection
            stmt.setString(1, transaction.getSenderAccountNumber());
            stmt.setString(2, transaction.getIfscCode());
            stmt.setString(3, transaction.getReceiverAccountNumber());
            stmt.setDouble(4, transaction.getAmount());
            stmt.setDate(5, new java.sql.Date(transaction.getDate().getTime())); // Convert java.util.Date to java.sql.Date
            stmt.setString(6, transaction.getNote());
            stmt.setString(7, transaction.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Find transactions by account number
    public List<BankTransaction> findTransactionsByAccountNumber(String accountNumber) {
        List<BankTransaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM bank_transactions WHERE sender_account_number = ? OR receiver_account_number = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, accountNumber);
            stmt.setString(2, accountNumber);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String senderAccount = rs.getString("sender_account_number");
                String ifscCode = rs.getString("ifsc_code");
                String receiverAccount = rs.getString("receiver_account_number");
                double amount = rs.getDouble("amount");
                Date transactionDate = rs.getDate("transaction_date");
                String note = rs.getString("note");
                String status = rs.getString("status");

                BankTransaction transaction = new BankTransaction(senderAccount, ifscCode, receiverAccount, amount, transactionDate, note, status);
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
