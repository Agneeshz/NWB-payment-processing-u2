package com.ezpay.payment.Service;

import com.ezpay.payment.Model.Transaction;
import com.ezpay.payment.Model.User;
import com.ezpay.payment.Repo.TransactionRepository;
import com.ezpay.payment.Repo.UserRepository;

import java.util.Date;

public class UPIService {
    private UserRepository userRepository;
    private TransactionRepository transactionRepository;

    public UPIService(UserRepository userRepository, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    // upi id verification
    public String verifyUpiId(String UpiId)
    {
        User sender = userRepository.findUserByUpiId(UpiId);

        if(sender == null)
        {
            return "Invalid UPI ID";
        }

        return "verified";
    }

    public String processPayment(String senderUpiId, String receiverUpiId, double amount, String note) {
        User sender = userRepository.findUserByUpiId(senderUpiId);
        User receiver = userRepository.findUserByUpiId(receiverUpiId);

        // Check if the account has sufficient balance
        if (sender.getBalance() < amount) {
            return "Error: Insufficient funds.";
        }

        // Deduct amount from sender and add to receiver
        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        // Record the transaction
        Transaction transaction = new Transaction(senderUpiId, receiverUpiId, amount, new Date(), note, "Success");
        transactionRepository.saveTransaction(transaction);

        System.out.println("Payment of " + amount + " from " + senderUpiId + " to " + receiverUpiId + " is done.");
        return "Transaction Successful.";
    }
}
