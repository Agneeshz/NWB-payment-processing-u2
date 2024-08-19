/**
 * Module Name: UPIService
 * 
 * Description: This module provides services related to UPI transactions. It verifies UPI details and ensures 
 * that a transaction can be completed based on the user's balance. If the balance is sufficient, the transaction 
 * is processed and the UPI balance is updated.
 * 
 * Author:
 * Adithya Mode
 * 
 * Date: August 10,2024
 */


package com.ezpay.payment.service;

import com.ezpay.payment.model.UPITransaction;
import com.ezpay.payment.model.UPI;
import com.ezpay.payment.repository.UPITransactionRepository;
import com.ezpay.payment.repository.UPIRepository;

import java.util.Date;

public class UPIService {
    private static UPIRepository upiRepository;
    private static UPITransactionRepository upitransactionRepository;

    public UPIService(UPIRepository upiRepository, UPITransactionRepository upitransactionRepository) {
        this.upiRepository = upiRepository;
        this.upitransactionRepository = upitransactionRepository;
    }

    // upi id verification
    public static String verifyUpiId(String UpiId)
    {
        UPI sender = upiRepository.findUserByUpiId(UpiId);

        if(sender == null)
        {
            return "Invalid UPI ID";
        }

        return "verified";
    }

    public static String processPayment(String senderUpiId, String receiverUpiId, double amount, String note) {
    	String accountVerificationResult = verifyUpiId(senderUpiId);
        if (accountVerificationResult.equals("Invalid UPI ID")) {
            return accountVerificationResult;
        }

        String receiverVerificationResult = verifyUpiId(senderUpiId);
        if (receiverVerificationResult.equals("Invalid UPI ID")) {
            return receiverVerificationResult;
        }
    	UPI sender = upiRepository.findUserByUpiId(senderUpiId);
        UPI receiver = upiRepository.findUserByUpiId(receiverUpiId);

        // Check if the account has sufficient balance
        if (sender.getBalance() < amount) {
            return "Error: Insufficient funds.";
        }

        // Deduct amount from sender and add to receiver
        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        // Record the transaction
        UPITransaction transaction = new UPITransaction(senderUpiId, receiverUpiId, amount, new Date(), note, "Success");
        upitransactionRepository.saveTransaction(transaction);

        System.out.println("Payment of " + amount + " from " + senderUpiId + " to " + receiverUpiId + " is done.");
        return "Transaction Successful.";
    }
}