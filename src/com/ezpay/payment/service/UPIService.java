/**
 * Module Name: UPIService
 *
 * Description: This interface defines the core operations for handling UPI (Unified Payments Interface)
 * transactions within the payment processing system. It includes methods for verifying UPI IDs, processing
 * payments between UPI accounts, and retrieving the transaction history for a specific UPI ID.
 *
 * Author:
 * Agneesh Dasgupta
 *
 * Date: August 23, 2024
 */

package com.ezpay.payment.service;
import java.util.List;
import com.ezpay.payment.model.UPITransaction;

public interface UPIService {
	String verifyUpiId(String upiId);
	String processPayment(String senderUpiId, String receiverUpiId, double amount, String note);
	List<UPITransaction> getTransactionHistory(String upiId);
}
