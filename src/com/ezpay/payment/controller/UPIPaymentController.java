package com.ezpay.payment.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.ezpay.payment.model.UPITransaction;
import com.ezpay.payment.repository.UPIRepository;
import com.ezpay.payment.repository.UPITransactionRepository;
import com.ezpay.payment.service.UPIService;
import com.ezpay.payment.util.DBConnection;

public class UPIPaymentController {

	private Connection connection;

    public UPIPaymentController() {
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database");
        }
    }
    
    public void UPIDetails() {
    	// Setup repositories with database connection
        UPIRepository userRepository = new UPIRepository(connection);
        UPITransactionRepository transactionRepository = new UPITransactionRepository(connection);
        
        // Setup UPI service
        UPIService upiService = new UPIService(userRepository, transactionRepository);
        
        // User inputs
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Transfer Funds");
            System.out.println("2. Check Transactions");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    transferFunds(scanner, upiService);
                    break;
                case 2:
                    checkTransactions(scanner, upiService);
                    break;
                case 3:
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
    

    public void transferFunds(Scanner scanner, UPIService upiService) {
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
                if(amount < 0)
                	System.out.println("Error: Transaction amount cannot be negative.");
                if(amount > 0)
                {
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
                		String result = upiService.processPayment(senderUpiId, receiverUpiId, amount, note);
                		System.out.println(result);
                	} else {
                		System.out.println("Transaction Aborted.");
                	}
                } else {
            		System.out.println("Error: Transaction amount must be greater than zero.");
            	}
            } else {
                	System.out.println(receiverUpiVerification);
                	System.out.println("Transaction cancelled !!");
                }
            } else {
        		System.out.println("Error: Cannot transfer money to the same UPI ID.");
        	}
        } else {
            System.out.println(senderUpiVerification);
            System.out.println("Transaction cancelled !!");
        }
    }
    private void checkTransactions(Scanner scanner, UPIService upiService) {
        System.out.println("Enter your UPI ID:");
        String upiId = scanner.nextLine();
        
        // Verify account number
        String verification = upiService.verifyUpiId(upiId);
        if (!verification.equals("verified")) {
            System.out.println("Invalid UPI ID."); // Updated message
            return; // Exit the method if account number is invalid
        }
        
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
