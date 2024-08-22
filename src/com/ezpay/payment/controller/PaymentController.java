/**
 * Module Name: PaymentController
 * 
 * Description: This module acts as the entry point for processing payments. It prompts the user to select a 
 * mode of payment (UPI or Bank Transfer) and directs the flow to the respective payment detail collection module.
 * 
 * Authors:
 * Agneesh Dasgputa
 * 
 * Date: August 10,2024
 */

package com.ezpay.payment.controller;


import java.util.Scanner;

public class PaymentController {

    public static void main(String[] args) {
        //Assuming user has already logged in
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while(flag) {
        System.out.println("Please enter an option for mode of payment:");
        System.out.println("1. UPI");
        System.out.println("2. Bank Transfer");
        System.out.println("3. Exit");

        int choice = scanner.nextInt(); // Read the user choice as an integer
        scanner.nextLine(); // Consume the newline character left after nextInt()

        switch (choice) {
            case 1:
                UPIPaymentController upiPaymentController = new UPIPaymentController();
                upiPaymentController.UPIDetails();
                flag = false;
                break;
            case 2:
                BankTransferPaymentController bankPaymentController = new BankTransferPaymentController();
                bankPaymentController.bankDetails();
                flag = false;
                break;
            case 3:
                System.out.println("Exiting...");
                flag = false;
                break;
            default:
                System.out.println("Invalid input. Please enter a valid option.");
                break;
        }
        }
        scanner.close();
    }
}