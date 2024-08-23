/**
 * Module Name: UPIPaymentController
 * 
 * Description: This module handles the user interaction for UPI payments. It provides functionality for
 * transferring funds and checking transaction history. The module interacts with the service layer to
 * process transactions and manage UPI details.
 * 
 * Authors:
 * Agneesh Dasgupta
 * 
 * Date: August 23, 2024
 */

package com.ezpay.payment.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.ezpay.payment.model.UPITransaction;
import com.ezpay.payment.repository.UPIRepositoryImpl;
import com.ezpay.payment.repository.UPITransactionRepositoryImpl;
import com.ezpay.payment.service.UPIServiceImpl;
import com.ezpay.payment.util.DBConnection;

public class UPIPaymentController {

	private Connection connection;

    public UPIPaymentController() {
        try {
            // Establishing a database connection
            connection = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database");
        }
    }
    
    public void UPIDetails() {
        // Setup repositories with database connection
        UPIRepositoryImpl userRepository = new UPIRepositoryImpl(connection);
        UPITransactionRepositoryImpl transactionRepository = new UPITransactionRepositoryImpl(connection);
        
        // Setup UPI service
        UPIServiceImpl upiService = new UPIServiceImpl(userRepository, transactionRepository);
        
        // User inputs
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display UPI payment options to the user
            System.out.println("Select an option:");
            System.out.println("1. Transfer Funds");
            System.out.println("2. Check Transactions");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            // Handle user's choice
            switch (choice) {
                case 1:
                    // Transfer funds option
                    transferFunds(scanner, upiService);
                    break;
                case 2:
                    // Check transaction history option
                    checkTransactions(scanner, upiService);
                    break;
                case 3:
                    // Exit option
                    System.out.println("Exiting...");
                    scanner.close();
                    closeConnection(); // Close database connection
                    return;
                default:
                    // Handle invalid input
                    System.out.println("Invalid choice. Please try again.");
            }
            
            // Prompt to continue or exit
            System.out.println("Do you wish to continue? (YES to continue, any other key to exit)");
            String continueOption = scanner.nextLine();
            if (!continueOption.equalsIgnoreCase("YES")) {
                System.out.println("Exiting...");
                scanner.close();
                closeConnection(); // Close database connection
                return;
            }
        }
    }
    

    public void transferFunds(Scanner scanner, UPIServiceImpl upiService) {
        // User inputs for UPI ID and transaction details
        System.out.println("Enter your UPI ID:");
        String senderUpiId = scanner.nextLine();

        // Verifying sender UPI ID
        String senderUpiVerification = upiService.verifyUpiId(senderUpiId);

        if (senderUpiVerification.equalsIgnoreCase("verified")) {
            System.out.println("Enter recipient UPI ID:");
            String receiverUpiId = scanner.nextLine();
            
            if(!senderUpiId.equals(receiverUpiId)) {
                // Verifying receiver UPI ID
                String receiverUpiVerification = upiService.verifyUpiId(receiverUpiId);

                if (receiverUpiVerification.equalsIgnoreCase("verified")) {
                    System.out.println("Enter amount to send:");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // consume newline
                    
                    // Validate transaction amount
                    if(amount < 0) {
                        System.out.println("Error: Transaction amount cannot be negative.");
                    } else if(amount > 0) {
                        System.out.println("Enter note for the transaction (optional):");
                        String note = scanner.nextLine();
        
                        // Confirm transaction details
                        System.out.println("Please confirm your details");
                        System.out.println("Sender UPI ID : " + senderUpiId);
                        System.out.println("Receiver UPI ID : " + receiverUpiId);
                        System.out.println("Amount : " + amount);
                        System.out.println("Enter YES to initiate the transaction");
                        String confirm = scanner.nextLine();
                        
                        if (confirm.equalsIgnoreCase("yes")) {
                            // Process payment through UPI service
                            String result = upiService.processPayment(senderUpiId, receiverUpiId, amount, note);
                            System.out.println(result);
                        } else {
                            System.out.println("Transaction Aborted.");
                        }
                    } else {
                        System.out.println("Error: Transaction amount must be greater than zero.");
                    }
                } else {
                    // Handle invalid receiver UPI ID
                    System.out.println(receiverUpiVerification);
                    System.out.println("Transaction cancelled !!");
                }
            } else {
                System.out.println("Error: Cannot transfer money to the same UPI ID.");
            }
        } else {
            // Handle invalid sender UPI ID
            System.out.println(senderUpiVerification);
            System.out.println("Transaction cancelled !!");
        }
    }

    private void checkTransactions(Scanner scanner, UPIServiceImpl upiService) {
        // User input for UPI ID to check transaction history
        System.out.println("Enter your UPI ID:");
        String upiId = scanner.nextLine();
        
        // Verify UPI ID
        String verification = upiService.verifyUpiId(upiId);
        if (!verification.equals("verified")) {
            System.out.println("Invalid UPI ID.");
            return; // Exit the method if UPI ID is invalid
        }
        
        // Fetch and display transaction history
        List<UPITransaction> transactions = upiService.getTransactionHistory(upiId);
        if (transactions.isEmpty()) {
            System.out.println("No transactions found for UPI ID: " + upiId);
        } else {
            System.out.println("Transaction History for UPI ID: " + upiId);
            for (UPITransaction transaction : transactions) {
                System.out.println("Sender: " + transaction.getSenderUpiId() +
                                   ", Receiver: " + transaction.getReceiverUpiId() +
                                   ", Amount: " + transaction.getAmount() +
                                   ", Date: " + transaction.getDate() +
                                   ", Note: " + transaction.getNote() +
                                   ", Status: " + transaction.getStatus());
            }
        }
    }

    // Method to close the database connection
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
