/**
 * Module Name: PaymentController
 * 
 * Description: This module acts as the entry point for processing payments. It prompts the user to select a 
 * mode of payment (UPI or Bank Transfer) and directs the flow to the respective payment detail collection module.
 * 
 * Authors:
 * Agneesh Dasgupta
 * 
 * Date: August 23, 2024
 */

package com.ezpay.payment.controller;

import java.util.Scanner;

public class PaymentController {

    public static void main(String[] args) {
        // Assuming the user has already logged in
        Scanner scanner = new Scanner(System.in);
        boolean flag = true; // Flag to control the loop
        while(flag) {
            // Display payment options to the user
            System.out.println("Please enter an option for mode of payment:");
            System.out.println("1. UPI");
            System.out.println("2. Bank Transfer");
            System.out.println("3. Exit");

            // Read the user's choice as an integer
            int choice = scanner.nextInt(); 
            scanner.nextLine(); // Consume the newline character left after nextInt()

            // Handle the user's choice
            switch (choice) {
                case 1:
                    // User selected UPI payment mode
                    UPIPaymentController upiPaymentController = new UPIPaymentController();
                    upiPaymentController.UPIDetails();
                    flag = false; // Exit the loop after processing UPI payment
                    break;
                case 2:
                    // User selected Bank Transfer payment mode
                    BankTransferPaymentController bankPaymentController = new BankTransferPaymentController();
                    bankPaymentController.bankDetails();
                    flag = false; // Exit the loop after processing Bank Transfer payment
                    break;
                case 3:
                    // User chose to exit
                    System.out.println("Exiting...");
                    flag = false; // Exit the loop
                    break;
                default:
                    // Handle invalid input
                    System.out.println("Invalid input. Please enter a valid option.");
                    break;
            }
        }
        // Close the scanner resource
        scanner.close();
    }
}
