/**
 * Module Name: BankAccountDetails
 * 
 * Description: This module is responsible for collecting bank account transaction details from the user.It 
 * confirms the details, and then initiates the transaction if confirmed by the user.
 * 
 * Author:
 * Deepak Reddy
 * 
 * Date: August 10,2024
 */

package com.ezpay.payment.controller;
import java.util.Scanner;
import com.ezpay.payment.service.BankAccountService;

public class BankAccountDetails 
{
    static Scanner scanner = new Scanner(System.in);
    
    // Get Bank account details from user
    static public void getBankAccountDetails()
    {
        System.out.println("Please enter your Account Number : ");
        String accountNumber = scanner.nextLine();
        
        System.out.println("Please enter your IFSC Code: ");
        String ifscCode = scanner.nextLine();

        System.out.println("Please enter the amount : ");
        int amount = scanner.nextInt();
        scanner.nextLine();
        
        // Confirm the details with the user
        System.out.println("Please confirm your details");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("IFSC Code : " + ifscCode);
        System.out.println("Amount : " + amount);
        System.out.println("Enter YES to initiate the transaction");
        String confirmation = scanner.nextLine();
        
        // If the user confirms, initiate the transaction
        if(confirmation.equalsIgnoreCase("YES"))
        {
            System.out.println("Please wait. Your transaction is in progress");
            BankAccountService.verifyDetails(accountNumber, amount);
        }
        
        // If the user does not confirm, abort the transaction
        else
        {
            System.out.println("Transaction is aborted");
        }

    }
}

