/**
 * Module Name: UPIDetails
 *
 * Description:This module is responsible for collecting UPI transaction details from the user.It confirms the 
 * details, and then initiates the transaction if confirmed by the user.
 * 
 * Author:
 * Adithya Mode
 * 
 * Date: August 10,2024
 */


package com.ezpay.payment.controller;

import java.util.Scanner;

import com.ezpay.payment.repository.UPIRepository;
import com.ezpay.payment.repository.UPITransactionRepository;
import com.ezpay.payment.service.UPIService;

public class UPIPaymentController {

    public void UPIDetails()
    {
        // Setup repositories
        UPIRepository userRepository = new UPIRepository();
        UPITransactionRepository transactionRepository = new UPITransactionRepository();
        
        // Setup UPI service
        UPIService upiService = new UPIService(userRepository, transactionRepository);
        
        // User inputs
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your UPI ID:");
        String senderUpiId = scanner.nextLine();

        //verifying sender upiid
        String senderUpiverification = upiService.verifyUpiId(senderUpiId);

        if(senderUpiverification.equalsIgnoreCase("verified"))
        {
            System.out.println("Enter recipient UPI ID:");
            String receiverUpiId = scanner.nextLine();

            //verifying receiver upi id
            String receiverUpiverification = upiService.verifyUpiId(receiverUpiId);

            if(receiverUpiverification.equalsIgnoreCase("verified"))
            {
                System.out.println("Enter amount to send:");
                double amount = scanner.nextDouble();
                scanner.nextLine(); // consume newline
        
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
            }

            else
            {
                System.out.println(receiverUpiverification);
                System.out.println("Transaction cancelled !!");
            }
        }

        else
        {
            System.out.println(senderUpiverification);
            System.out.println("Transaction cancelled !!");
        }

        scanner.close();
    }

}

