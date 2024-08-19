/**
 * Module Name: BankAccountService
 * 
 * Description: This module provides services related to bank account transactions. It verifies account details 
 * and ensures that a transaction can be completed based on the account balance. If the balance is sufficient, 
 * the transaction is processed and the account balance is updated.
 * 
 * Author:
 * Deepak Reddy
 * 
 * Date: August 10,2024
 */


package com.ezpay.payment.service;

import com.ezpay.payment.model.BankTransaction;
import com.ezpay.payment.model.BankUser;
import com.ezpay.payment.repository.BankTransactionRepository;
import com.ezpay.payment.repository.BankUserRepository;


import java.util.Date;

public class BankService {

    private static BankUserRepository bankuserRepository;
    private static BankTransactionRepository banktransactionRepository;

    public BankService(BankUserRepository bankuserRepository, BankTransactionRepository banktransactionRepository) {
        this.bankuserRepository = bankuserRepository;
        this.banktransactionRepository = banktransactionRepository;
    }

    //account number verification
    public static String verifyAccountNumber(String accountNumber)
    {
        BankUser sender = bankuserRepository.findUserByAccountNumber(accountNumber);

        if(sender == null)
        {
            return "Invalid Account Number";
        }

        return "verified";
    }

    //ifsc code verification
    public static String verifyIfscCode(String accountNumber, String ifscCode)
    {
        BankUser sender = bankuserRepository.findUserByAccountNumber(accountNumber);

        if (sender == null || !sender.getIfscCode().equalsIgnoreCase(ifscCode)) {
            return "Invalid IFSC Code";
        }

        return "verified";
    }

    public static String processPayment(String senderAccountNumber, String ifscCode, String receiverAccountNumber, double amount, String note) {
    	String accountVerificationResult = verifyAccountNumber(senderAccountNumber);
        if (accountVerificationResult.equals("Invalid Account Number")) {
            return accountVerificationResult;
        }

        String ifscVerificationResult = verifyIfscCode(senderAccountNumber, ifscCode);
        if (ifscVerificationResult.equals("Invalid IFSC Code")) {
            return ifscVerificationResult;
        }

        String receiverVerificationResult = verifyAccountNumber(receiverAccountNumber);
        if (receiverVerificationResult.equals("Invalid Account Number")) {
            return receiverVerificationResult;
        }
    	BankUser sender = bankuserRepository.findUserByAccountNumber(senderAccountNumber);
        BankUser receiver = bankuserRepository.findUserByAccountNumber(receiverAccountNumber);

        // Check if the account has sufficient balance
        if (sender.getBalance() < amount) {
            return "Error: Insufficient funds.";
        }

        // Deduct amount from sender and add to receiver
        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        // Record the transaction
        BankTransaction bankTransaction = new BankTransaction(senderAccountNumber, ifscCode, receiverAccountNumber, amount, new Date(), note, "Success");
        banktransactionRepository.saveTransaction(bankTransaction);

        System.out.println("Payment of " + amount + " from " + sender.getCustName() + " ACCOUNT NUMBER :" + senderAccountNumber + " To " + receiver.getCustName() + " ACCOUNT NUMBER : " + receiverAccountNumber + " is done.");
        return "Transaction Successful.";
    }

}