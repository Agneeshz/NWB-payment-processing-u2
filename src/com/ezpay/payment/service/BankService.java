/**
 * Module Name: BankService
 *
 * Description: This interface defines the contract for bank-related services within the payment processing
 * system. It provides methods for verifying account numbers and IFSC codes, processing payments between 
 * accounts, retrieving account balances, and obtaining transaction histories. Implementations of this 
 * interface will handle the specific logic for interacting with bank systems to perform these operations.
 * 
 * Author:
 * Hasini Sai Ramya
 * 
 * Date: August 23, 2024
 */

package com.ezpay.payment.service;
import java.util.List;
import com.ezpay.payment.model.BankTransaction;

public interface BankService {
	String verifyAccountNumber(String accountNumber);
	String verifyIfscCode(String accountNumber, String ifscCode);
	String processPayment(String senderAccountNumber, String ifscCode, String receiverAccountNumber, double amount, String note);
	double getBalance(String accountNumber);
	List<BankTransaction> getTransactionHistory(String accountNumber);
}
