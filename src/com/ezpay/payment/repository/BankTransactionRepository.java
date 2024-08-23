/**
 * 
 */
package com.ezpay.payment.repository;

import java.util.List;

import com.ezpay.payment.model.BankTransaction;

/**
 * @author Hasini Sai Ramya
 */
public interface BankTransactionRepository {
	void saveTransaction(BankTransaction transaction);
	String QUERY_INSERT_BANKTRANSACTIONS = "INSERT INTO bank_transactions (sender_account_number, ifsc_code, receiver_account_number, amount, transaction_date, note, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
	List<BankTransaction> findTransactionsByAccountNumber(String accountNumber);
	String QUERY_FIND_BANKTRANSACTIONS = "SELECT * FROM bank_transactions WHERE sender_account_number = ? OR receiver_account_number = ?";
}
