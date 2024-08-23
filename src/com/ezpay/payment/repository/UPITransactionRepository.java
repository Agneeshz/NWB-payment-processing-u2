/**
 * Module Name: UPITransactionRepository
 *
 * Description: This module defines the repository interface for handling UPI transactions in the payment processing system.
 * It provides methods for saving a UPI transaction and retrieving transactions based on the UPI ID of the sender or receiver.
 * The interface includes SQL queries for inserting and finding UPI transactions in the database.
 * 
 * Author:
 * Agneesh Dasgupta
 * 
 * Date: August 23, 2024
 */

package com.ezpay.payment.repository;
import java.util.List;
import com.ezpay.payment.model.UPITransaction;

public interface UPITransactionRepository {
	void saveTransaction(UPITransaction transaction);
	String QUERY_INSERT_UPITRANSACTIONS = "INSERT INTO upi_transactions (sender_upi_id, receiver_upi_id, amount, transaction_date, note, status) VALUES (?, ?, ?, ?, ?, ?)";
	List<UPITransaction> findTransactionsByUpiId(String upiId);
	String QUERY_FIND_UPITRANSACTIONS = "SELECT * FROM upi_transactions WHERE sender_upi_id = ? OR receiver_upi_id = ?";
}
