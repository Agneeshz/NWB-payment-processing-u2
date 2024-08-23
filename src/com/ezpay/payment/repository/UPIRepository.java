/**
 * 
 */
package com.ezpay.payment.repository;

import com.ezpay.payment.model.UPI;

/**
 * @author Agneesh Dasgupta
 */
public interface UPIRepository {
	UPI findUserByUpiId(String upiId);
	String QUERY_USERID = "SELECT * FROM upi WHERE upi_id = ?";
	void updateUser(UPI upi);
	String QUERY_UPDATE = "UPDATE upi SET balance = ? WHERE upi_id = ?";
}
