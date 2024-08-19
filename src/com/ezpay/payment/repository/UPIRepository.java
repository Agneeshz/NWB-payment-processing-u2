/**
 * Module Name: UPIRepository
 * 
 * Description:This module serves as the repository for storing and managing UPI details.
 * It contains a list of predefined bank accounts and provides functionality to update account details such as
 * balance after a transaction.
 * 
 * Author:
 * Adithya Mode
 * 
 * Date: August 10,2024
 */

package com.ezpay.payment.repository;

import com.ezpay.payment.model.UPI;
import com.ezpay.payment.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UPIRepository {
	private Connection connection;
    public UPIRepository(Connection connection) {
        this.connection = connection;
    }
    public UPI findUserByUpiId(String upiId) {
        try (Connection conn = DBConnection.getConnection()) { // Changed to DBConnection
            String query = "SELECT * FROM upi WHERE upi_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, upiId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String custName = rs.getString("cust_name");
                double balance = rs.getDouble("balance");
                long mobileNumber = rs.getLong("mobile_number");
                String email = rs.getString("email");
                return new UPI(custName, upiId, balance, mobileNumber, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateUser(UPI upi) {
        try (Connection conn = DBConnection.getConnection()) { // Changed to DBConnection
            String query = "UPDATE upi SET balance = ? WHERE upi_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDouble(1, upi.getBalance());
            stmt.setString(2, upi.getUpiId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

