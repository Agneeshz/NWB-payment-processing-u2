package com.ezpay.payment.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.ezpay.payment.repository.UPIRepository;
import com.ezpay.payment.repository.UPITransactionRepository;
import com.ezpay.payment.service.UPIService;

public class UPIPaymentController {

    private Connection connection;

    public UPIPaymentController() {
        try {
            // Setup JDBC connection
            String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe"; // Update this URL based on your DB
            String jdbcUser = "system"; // Update with your DB user
            String jdbcPassword = "natwest123"; // Update with your DB password
            connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
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

        scanner.close();
        
        // Close JDBC connection
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
