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

	public UPITransactionRepository(Connection connection) {
	    this.connection = connection;
	}
    public void saveTransaction(UPITransaction transaction) {
        try (Connection conn = DBConnection.getConnection()) { // Changed to DBConnection
            String query = "INSERT INTO upi_transactions (sender_upi_id, receiver_upi_id, amount, transaction_date, note, status) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, transaction.getSenderUpiId());
            statement.setString(2, transaction.getReceiverUpiId());
            statement.setDouble(3, transaction.getAmount());
            statement.setDate(4, new java.sql.Date(transaction.getDate().getTime()));
            statement.setString(5, transaction.getNote());
            statement.setString(6, transaction.getStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<UPITransaction> findTransactionsByUpiId(String upiId) {
        List<UPITransaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM upi_transactions WHERE sender_upi_id = ? OR receiver_upi_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, upiId);
            statement.setString(2, upiId);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                String senderUpiId = result.getString("sender_upi_id");
                String receiverUpiId = result.getString("receiver_upi_id");
                double amount = result.getDouble("amount");
                Date transactionDate = result.getDate("transaction_date");
                String note = result.getString("note");
                String status = result.getString("status");

                UPITransaction transaction = new UPITransaction(senderUpiId, receiverUpiId, amount, transactionDate, note, status);
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}

