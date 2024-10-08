/**
 * Module Name: BankTransferPaymentController
 *
 * Description: This module handles user interactions, allowing users to check their bank balance, transfer funds 
 * between accounts, and view their transaction history.User input is captured through the console, providing a simple interface for 
 * managing bank transactions. 
 *
 * Author:
 * Hasini Sai Ramya
 * 
 * Date: August 20, 2024
 * 
 */

package com.ezpay.payment.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.List;

import com.ezpay.payment.model.BankTransaction;
import com.ezpay.payment.repository.BankUserRepositoryImpl;
import com.ezpay.payment.repository.BankTransactionRepositoryImpl;
import com.ezpay.payment.service.BankServiceImpl;
import com.ezpay.payment.util.DBConnection;

public class BankTransferPaymentController {

	private Connection connection;

    public BankTransferPaymentController() {
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database");
        }
    }


    public void bankDetails() {
        BankUserRepositoryImpl bankUserRepository = new BankUserRepositoryImpl(connection);
        BankTransactionRepositoryImpl bankTransactionRepository = new BankTransactionRepositoryImpl(connection);
        BankServiceImpl bankService = new BankServiceImpl(bankUserRepository, bankTransactionRepository);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Transfer Funds");
            System.out.println("3. Check Transactions");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    checkBalance(scanner, bankService);
                    break;
                case 2:
                    transferFunds(scanner, bankService);
                    break;
                case 3:
                    checkTransactions(scanner, bankService);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    closeConnection();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            
            System.out.println("Do you wish to continue? (YES to continue, any other key to exit)");
            String continueOption = scanner.nextLine();
            if (!continueOption.equalsIgnoreCase("YES")) {
                System.out.println("Exiting...");
                scanner.close();
                closeConnection();
                return;
            }
        }
    }

    private void checkBalance(Scanner scanner, BankServiceImpl bankService) {
        System.out.println("Enter your Account Number:");
        String accountNumber = scanner.nextLine();

        // Verify account number
        String verification = bankService.verifyAccountNumber(accountNumber);
        if (!verification.equals("verified")) {
            System.out.println("Invalid account number."); // Updated message
            return; // Exit the method if account number is invalid
        }

        double balance = bankService.getBalance(accountNumber);
        System.out.println("Your current balance is: " + balance);
    }

    private void transferFunds(Scanner scanner, BankServiceImpl bankService) {
        System.out.println("Enter your Account Number:");
        String senderAccountNumber = scanner.nextLine();

        // Verify sender account number
        String senderVerification = bankService.verifyAccountNumber(senderAccountNumber);
        if (!senderVerification.equals("verified")) {
            System.out.println("Invalid account number."); // Updated message
            return; // Exit the method if account number is invalid
        }

        // Prompt for IFSC code
        System.out.println("Enter IFSC Code:");
        String ifscCode = scanner.nextLine();

        // Verify IFSC code
        String ifscVerification = bankService.verifyIfscCode(senderAccountNumber, ifscCode);
        if (!ifscVerification.equals("verified")) {
            System.out.println(ifscVerification);
            return; // Exit the method if IFSC code is invalid
        }

        // Prompt for recipient account number
        System.out.println("Enter recipient Account Number:");
        String receiverAccountNumber = scanner.nextLine();

        // Verify receiver account number
        String receiverVerification = bankService.verifyAccountNumber(receiverAccountNumber);
        if (!receiverVerification.equals("verified")) {
            System.out.println("Invalid account number."); // Updated message
            return; // Exit the method if receiver account number is invalid
        }

        // Check if sender and receiver account numbers are the same
        if (senderAccountNumber.equals(receiverAccountNumber)) {
            System.out.println("Error: Sender and receiver account numbers cannot be the same.");
            return; // Exit the method if they are the same
        }

        // Prompt for amount to send
        System.out.println("Enter amount to send:");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        // Validate the amount for negative values
        if (amount < 0) {
            System.out.println("Invalid amount. Amount cannot be negative.");
            return; // Exit the method if the amount is invalid
        }

        // Prompt for transaction note
        System.out.println("Enter note for the transaction (optional):");
        String note = scanner.nextLine();

        // Confirm transaction details
        System.out.println("Please confirm your details:");
        System.out.println("Sender Account Number: " + senderAccountNumber);
        System.out.println("Receiver Account Number: " + receiverAccountNumber);
        System.out.println("Amount: " + amount);
        System.out.println("Note: " + note);
        System.out.println("Enter YES to initiate the transaction:");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("YES")) {
            // Call the processPayment method to handle all verification and processing
            String result = bankService.processPayment(senderAccountNumber, ifscCode, receiverAccountNumber, amount, note);
            System.out.println(result);
        } else {
            System.out.println("Transaction canceled.");
        }
    }
    
    private void checkTransactions(Scanner scanner, BankServiceImpl bankService) {
        System.out.println("Enter your Account Number:");
        String accountNumber = scanner.nextLine();
        
        // Verify account number
        String verification = bankService.verifyAccountNumber(accountNumber);
        if (!verification.equals("verified")) {
            System.out.println("Invalid account number."); // Updated message
            return; // Exit the method if account number is invalid
        }
        
        List<BankTransaction> transactions = bankService.getTransactionHistory(accountNumber);
        if (transactions.isEmpty()) {
            System.out.println("No transactions found for account number: " + accountNumber);
        } else {
            System.out.println("Transaction History for Account Number: " + accountNumber);
            for (BankTransaction transaction : transactions) {
                System.out.println("Sender: " + transaction.getSenderAccountNumber() +
                                   ", Receiver: " + transaction.getReceiverAccountNumber() +
                                   ", Amount: " + transaction.getAmount() +
                                   ", Date: " + transaction.getDate() +
                                   ", Note: " + transaction.getNote() +
                                   ", Status: " + transaction.getStatus());
            }
        }
    }

    private void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
