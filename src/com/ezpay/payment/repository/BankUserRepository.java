/**
 * Module description: This module provides methods for saving transactions and finding transactions based on a Bank account details.
 * It helps in implementing classes to perform database operations related to Bank transactions. The queries defined within this interface help 
 * to insert new transactions and retrieve transaction records based on the sender's or receiver's Bank account details.
 *
 * @author Hasini Sai Ramya
 *
 * Date: August 23, 2024
 */

package com.ezpay.payment.repository;
import com.ezpay.payment.model.BankUser;

public interface BankUserRepository {
	BankUser findUserByAccountNumber(String accountNumber);
	String QUERY_BANK_USER = "SELECT * FROM bank_user WHERE account_number = ?";
	void updateUser(BankUser bankUser);
	String QUERY_UPDATE = "UPDATE bank_user SET balance = ? WHERE account_number = ?";
}
