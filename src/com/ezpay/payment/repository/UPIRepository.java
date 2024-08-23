/**
 * Module description: This module provides methods for saving transactions and finding transactions based on a UPI Id.
 * It helps in implementing classes to perform database operations related to UPI transactions. The queries defined within this interface help 
 * to insert new transactions and retrieve transaction records based on the sender's or receiver's UPI Id.
 *
 * @author Agneesh Dasgupta
 **/

package com.ezpay.payment.repository;
import com.ezpay.payment.model.UPI;

public interface UPIRepository {
	UPI findUserByUpiId(String upiId);
	String QUERY_USERID = "SELECT * FROM upi WHERE upi_id = ?";
	void updateUser(UPI upi);
	String QUERY_UPDATE = "UPDATE upi SET balance = ? WHERE upi_id = ?";
}
