/**
 * Module Name: BankTransactionRepository
 *
 * Description: This module provides an interface for interacting with the `bank_transactions` table in the database. 
 * It defines methods for saving bank transactions and retrieving transaction history based on account numbers.
 * The interface includes SQL query strings for inserting a new bank transaction and finding transactions by 
 * account number. Implementing classes should use these methods to manage bank transactions in a persistent store.
 * 
 * Author:
 * Hasini Sai Ramya
 * 
 * Date: August 23, 2024
 */

package com.ezpay.payment.repository;
import java.util.List;
import com.ezpay.payment.model.BankTransaction;

public interface BankTransactionRepository {
	void saveTransaction(BankTransaction transaction);
	String QUERY_INSERT_BANKTRANSACTIONS = "INSERT INTO bank_transactions (sender_account_number, ifsc_code, receiver_account_number, amount, transaction_date, note, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
	List<BankTransaction> findTransactionsByAccountNumber(String accountNumber);
	String QUERY_FIND_BANKTRANSACTIONS = "SELECT * FROM bank_transactions WHERE sender_account_number = ? OR receiver_account_number = ?";
}
