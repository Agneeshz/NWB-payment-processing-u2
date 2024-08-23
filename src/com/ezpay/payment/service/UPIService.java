/**
 * 
 */
package com.ezpay.payment.service;

import java.util.List;

import com.ezpay.payment.model.UPITransaction;

/**
 * @author Agneesh Dasgupta
 */
public interface UPIService {
	String verifyUpiId(String upiId);
	String processPayment(String senderUpiId, String receiverUpiId, double amount, String note);
	List<UPITransaction> getTransactionHistory(String upiId);
}
