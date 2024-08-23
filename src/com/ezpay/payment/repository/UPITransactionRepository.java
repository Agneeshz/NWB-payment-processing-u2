/**
 * 
 */
package com.ezpay.payment.repository;

import java.util.List;

import com.ezpay.payment.model.UPITransaction;

/**
 * @author Agneesh Dasgupta
 */
public interface UPITransactionRepository {
	void saveTransaction(UPITransaction transaction);
	String QUERY_INSERT_UPITRANSACTIONS = "INSERT INTO upi_transactions (sender_upi_id, receiver_upi_id, amount, transaction_date, note, status) VALUES (?, ?, ?, ?, ?, ?)";
	List<UPITransaction> findTransactionsByUpiId(String upiId);
	String QUERY_FIND_UPITRANSACTIONS = "SELECT * FROM upi_transactions WHERE sender_upi_id = ? OR receiver_upi_id = ?";
}
