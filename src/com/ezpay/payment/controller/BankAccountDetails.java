package com.ezpay.payment.controller;
import java.util.Scanner;
import com.ezpay.payment.service.BankAccountService;

public class BankAccountDetails 
{
    static Scanner scanner = new Scanner(System.in);

    static public void getBankAccountDetails()
    {
        System.out.println("Please enter your Account Number : ");
        String accountNumber = scanner.nextLine();
        
        System.out.println("Please enter your IFSC Code: ");
        String ifscCode = scanner.nextLine();

        System.out.println("Please enter the amount : ");
        int amount = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Please confirm your details");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("IFSC Code : " + ifscCode);
        System.out.println("Amount : " + amount);
        System.out.println("Enter YES to initiate the transaction");
        String confirmation = scanner.nextLine();

        if(confirmation.equalsIgnoreCase("YES"))
        {
            System.out.println("Please wait. Your transaction is in progress");
            BankAccountService.verifyDetails(accountNumber, amount);
        }
            
        else
        {
            System.out.println("Transaction is aborted");
        }

    }
}

