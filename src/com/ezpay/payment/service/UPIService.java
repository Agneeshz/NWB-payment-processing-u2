/**
 * Module Name: UPIService
 * 
 * Description: This module provides services related to UPI transactions. It verifies UPI details and ensures 
 * that a transaction can be completed based on the user's balance. If the balance is sufficient, the transaction 
 * is processed and the UPI balance is updated. Additionally, the service allows retrieval of transaction history 
 * for a specific UPI ID.
 * 
 * Author:
 * Agneesh Dasgupta
 * 
 * Date: August 23, 2024
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

    // Constructor to initialize UPIService with required repositories
    public UPIService(UPIRepository upiRepository, UPITransactionRepository upiTransactionRepository) {
        this.upiRepository = upiRepository;
        this.upiTransactionRepository = upiTransactionRepository;
    }

    /**
     * Verifies if the provided UPI ID exists in the system.
     * 
     * @param upiId The UPI ID to be verified.
     * @return A string indicating whether the UPI ID is valid or not.
     */
    public String verifyUpiId(String upiId) {
        UPI user = upiRepository.findUserByUpiId(upiId);
        return user == null ? "Invalid UPI ID" : "verified";
    }

    /**
     * Processes a payment transaction between two UPI IDs.
     * 
     * @param senderUpiId The UPI ID of the sender.
     * @param receiverUpiId The UPI ID of the receiver.
     * @param amount The amount to be transferred.
     * @param note A note to be included with the transaction.
     * @return A string indicating the result of the transaction.
     */
    public String processPayment(String senderUpiId, String receiverUpiId, double amount, String note) {
        // Check if sender or receiver UPI IDs are null
        if (senderUpiId == null || receiverUpiId == null) {
            return "Error: UPI ID cannot be null.";
        }

        // Verify sender's UPI ID
        String senderVerification = verifyUpiId(senderUpiId);
        if (senderVerification.equals("Invalid UPI ID")) {
            return senderVerification;
        }

        // Verify receiver's UPI ID
        String receiverVerification = verifyUpiId(receiverUpiId);
        if (receiverVerification.equals("Invalid UPI ID")) {
            return receiverVerification;
        }
        
        // Check if the sender and receiver UPI IDs are the same
        if (senderUpiId.equals(receiverUpiId)) {
            return "Error: Cannot transfer money to the same UPI ID.";
        }

        UPI sender = upiRepository.findUserByUpiId(senderUpiId);
        UPI receiver = upiRepository.findUserByUpiId(receiverUpiId);

        // Validate the transaction amount
        if (amount < 0) {
            return "Error: Transaction amount cannot be negative.";
        }
        if (amount == 0) {
            return "Error: Transaction amount must be greater than zero.";
        }

        // Check if the sender has sufficient funds
        if (sender.getBalance() < amount) {
            return "Error: Insufficient funds.";
        }

        // Update balances for both sender and receiver
        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        // Update the user details in the database
        upiRepository.updateUser(sender);
        upiRepository.updateUser(receiver);

        // Create and save the transaction
        UPITransaction transaction = new UPITransaction(senderUpiId, receiverUpiId, amount, new Date(), note, "Success");
        upiTransactionRepository.saveTransaction(transaction);

        return "Transaction Successful.";
    }

    /**
     * Retrieves the transaction history for a specific UPI ID.
     * 
     * @param upiId The UPI ID whose transaction history is to be retrieved.
     * @return A list of UPITransaction objects representing the transaction history.
     */
    public List<UPITransaction> getTransactionHistory(String upiId) {
        return upiTransactionRepository.findTransactionsByUpiId(upiId);
    }
}
