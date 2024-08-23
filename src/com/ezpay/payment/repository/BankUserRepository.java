/**
 * Module description: This module provides methods for saving UPI transactions and finding transactions based on a Bank account details.
 * It helps in implementing classes to perform database operations related to Bank transactions. The queries defined within this interface help 
 * to insert new transactions and retrieve transaction records based on the sender's or receiver's Bank account details.
 */
package com.ezpay.payment.repository;

import com.ezpay.payment.model.BankUser;

/**
 * @author Hasini Sai Ramya
 */
public interface BankUserRepository {
	BankUser findUserByAccountNumber(String accountNumber);
	String QUERY_BANK_USER = "SELECT * FROM bank_user WHERE account_number = ?";
	void updateUser(BankUser bankUser);
	String QUERY_UPDATE = "UPDATE bank_user SET balance = ? WHERE account_number = ?";
}
