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

        System.out.println("Please enter the mode of payment");
        System.out.println("UPI or Bank Transfer ?");
        String modeOfPayment = scanner.nextLine();

        if(modeOfPayment.equalsIgnoreCase("UPI"))
        {
            UPIPaymentController upiPaymentController = new UPIPaymentController();
            upiPaymentController.UPIDetails();
        }

        else if(modeOfPayment.equalsIgnoreCase("Bank Transfer"))
        {
        	BankTransferPaymentController bankPaymentController = new BankTransferPaymentController();
            bankPaymentController.bankDetails();
        }

        scanner.close();
    }
}