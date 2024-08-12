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
import com.ezpay.payment.service.UPIService;

public class UPIDetails 
{
    static Scanner scanner = new Scanner(System.in);

    // Get UPI Details from user
    static public void getUPIDetails()
    {
        System.out.println("Please enter your UPI ID : ");
        String upiId = scanner.nextLine();

        System.out.println("Please enter the amount : ");
        int amount = scanner.nextInt();
        scanner.nextLine();
        
        // Confirm the details with the user
        System.out.println("Please confirm your details");
        System.out.println("UPI ID : " + upiId);
        System.out.println("Amount : " + amount);
        System.out.println("Enter YES to initiate the transaction");
        String confirmation = scanner.nextLine();
        
        // If the user confirms, initiate the transaction
        if(confirmation.equalsIgnoreCase("YES"))
        {
            System.out.println("Please wait. Your transaction is in progress");
            UPIService.verifyDetails(upiId, amount);
        }
        
        // If the user does not confirm, abort the transaction
        else
        {
            System.out.println("Transaction is aborted");
        }

    }
}

