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
import java.util.List;

public class UPIService {
    private UPIRepository upiRepository;
    private UPITransactionRepository upiTransactionRepository;

    public UPIService(UPIRepository upiRepository, UPITransactionRepository upiTransactionRepository) {
        this.upiRepository = upiRepository;
        this.upiTransactionRepository = upiTransactionRepository;
    }

    public String verifyUpiId(String upiId) {
        UPI user = upiRepository.findUserByUpiId(upiId);
        return user == null ? "Invalid UPI ID" : "verified";
    }

    public String processPayment(String senderUpiId, String receiverUpiId, double amount, String note) {
        String senderVerification = verifyUpiId(senderUpiId);
        if (senderVerification.equals("Invalid UPI ID")) {
            return senderVerification;
        }

        String receiverVerification = verifyUpiId(receiverUpiId);
        if (receiverVerification.equals("Invalid UPI ID")) {
            return receiverVerification;
        }
        
        if(senderUpiId.equals(receiverUpiId)) {
        	return "Error: Cannot transfer money to the same UPI ID.";
        }

        UPI sender = upiRepository.findUserByUpiId(senderUpiId);
        UPI receiver = upiRepository.findUserByUpiId(receiverUpiId);

        if(amount<0) {
        	return "Error: Transaction amount cannot be negative.";
        }
        if(amount == 0) {
        	return "Error: Transaction amount must be greater than zero.";
        }

        if (sender.getBalance() < amount) {
            return "Error: Insufficient funds.";
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        upiRepository.updateUser(sender);
        upiRepository.updateUser(receiver);

        UPITransaction transaction = new UPITransaction(senderUpiId, receiverUpiId, amount, new Date(), note, "Success");
        upiTransactionRepository.saveTransaction(transaction);

        return "Transaction Successful.";
    }
 // Get transaction history for the user
    public List<UPITransaction> getTransactionHistory(String upiId) {
        return upiTransactionRepository.findTransactionsByUpiId(upiId);
    }
}
