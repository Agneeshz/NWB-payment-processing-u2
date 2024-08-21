package com.ezpay.payment.repository;

import com.ezpay.payment.model.UPITransaction;
import com.ezpay.payment.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}

