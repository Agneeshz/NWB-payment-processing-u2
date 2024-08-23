/**
 * Module description: This module takes the classes and implements the repositories using queries.
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
