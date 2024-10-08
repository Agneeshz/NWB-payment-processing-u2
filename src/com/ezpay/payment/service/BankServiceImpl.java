/**
 * Module Name: BankService
 *
 * Description: The BankService class providesmethods for verifying account numbers and IFSC codes, processing payments, 
 * retrieving user balances, and fetching transaction histories. .
 * 
 * Author:
 * Hasini Sai Ramya
 * 
 * Date: August 20, 2024
 * 
 */


package com.ezpay.payment.service;

import com.ezpay.payment.model.BankTransaction;
import com.ezpay.payment.model.BankUser;
import com.ezpay.payment.repository.BankTransactionRepositoryImpl;
import com.ezpay.payment.repository.BankUserRepositoryImpl;

import java.util.Date;
import java.util.List;

public class BankServiceImpl implements BankService {
    private BankUserRepositoryImpl bankUserRepository;
    private BankTransactionRepositoryImpl bankTransactionRepository;

    public BankServiceImpl(BankUserRepositoryImpl bankUserRepository, BankTransactionRepositoryImpl bankTransactionRepository) {
        this.bankUserRepository = bankUserRepository;
        this.bankTransactionRepository = bankTransactionRepository;
    }

    // Verify account number
    public String verifyAccountNumber(String accountNumber) {
        BankUser user = bankUserRepository.findUserByAccountNumber(accountNumber);
        return user == null ? "Invalid Account Number" : "verified";
    }

    // Verify IFSC code
    public String verifyIfscCode(String accountNumber, String ifscCode) {
        BankUser user = bankUserRepository.findUserByAccountNumber(accountNumber);
        return (user == null || !user.getIfscCode().equalsIgnoreCase(ifscCode)) ? "Invalid IFSC Code" : "verified";
    }

    // Process bank payment
    public String processPayment(String senderAccountNumber, String ifscCode, String receiverAccountNumber, double amount, String note) {
        String senderVerification = verifyAccountNumber(senderAccountNumber);
        if (!senderVerification.equals("verified")) {
            return senderVerification;
        }

        String ifscVerification = verifyIfscCode(senderAccountNumber, ifscCode);
        if (!ifscVerification.equals("verified")) {
            return ifscVerification;
        }

        String receiverVerification = verifyAccountNumber(receiverAccountNumber);
        if (!receiverVerification.equals("verified")) {
            return receiverVerification;
        }

        BankUser sender = bankUserRepository.findUserByAccountNumber(senderAccountNumber);
        BankUser receiver = bankUserRepository.findUserByAccountNumber(receiverAccountNumber);

        if (sender.getBalance() < amount) {
            return "Error: Insufficient funds.";
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        bankUserRepository.updateUser(sender);
        bankUserRepository.updateUser(receiver);

        BankTransaction transaction = new BankTransaction(senderAccountNumber, ifscCode, receiverAccountNumber, amount, new Date(), note, "Success");
        bankTransactionRepository.saveTransaction(transaction);

        return "Transaction Successful.";
    }
    
    // Get user balance
    public double getBalance(String accountNumber) {
        BankUser user = bankUserRepository.findUserByAccountNumber(accountNumber);
        return user != null ? user.getBalance() : 0.0;
    }

    // Get transaction history for the user
    public List<BankTransaction> getTransactionHistory(String accountNumber) {
        return bankTransactionRepository.findTransactionsByAccountNumber(accountNumber);
    }
}
