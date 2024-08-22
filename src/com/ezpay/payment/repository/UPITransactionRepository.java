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
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, transaction.getSenderUpiId());
            stmt.setString(2, transaction.getReceiverUpiId());
            stmt.setDouble(3, transaction.getAmount());
            stmt.setDate(4, new java.sql.Date(transaction.getDate().getTime()));
            stmt.setString(5, transaction.getNote());
            stmt.setString(6, transaction.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<UPITransaction> findTransactionsByUpiId(String upiId) {
        List<UPITransaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM upi_transactions WHERE sender_upi_id = ? OR receiver_upi_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, upiId);
            stmt.setString(2, upiId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String senderUpiId = rs.getString("sender_upi_id");
                String receiverUpiId = rs.getString("receiver_upi_id");
                double amount = rs.getDouble("amount");
                Date transactionDate = rs.getDate("transaction_date");
                String note = rs.getString("note");
                String status = rs.getString("status");

                UPITransaction transaction = new UPITransaction(senderUpiId, receiverUpiId, amount, transactionDate, note, status);
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}

