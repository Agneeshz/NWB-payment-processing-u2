/**
 * 
 */
package com.ezpay.payment.service;

import java.util.List;

import com.ezpay.payment.model.BankTransaction;

/**
 * @author Hasini Sai Ramya
 */
public interface BankService {
	String verifyAccountNumber(String accountNumber);
	String verifyIfscCode(String accountNumber, String ifscCode);
	String processPayment(String senderAccountNumber, String ifscCode, String receiverAccountNumber, double amount, String note);
	double getBalance(String accountNumber);
	List<BankTransaction> getTransactionHistory(String accountNumber);
}
