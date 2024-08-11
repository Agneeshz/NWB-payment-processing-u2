package com.ezpay.payment.controller;
import java.util.Scanner;
import com.ezpay.payment.service.UPIService;

public class UPIDetails 
{
    static Scanner scanner = new Scanner(System.in);

    static public void getUPIDetails()
    {
        System.out.println("Please enter your UPI ID : ");
        String upiId = scanner.nextLine();

        System.out.println("Please enter the amount : ");
        int amount = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Please confirm your details");
        System.out.println("UPI ID : " + upiId);
        System.out.println("Amount : " + amount);
        System.out.println("Enter YES to initiate the transaction");
        String confirmation = scanner.nextLine();

        if(confirmation.equalsIgnoreCase("YES"))
        {
            System.out.println("Please wait. Your transaction is in progress");
            UPIService.verifyDetails(upiId, amount);
        }
            
        else
        {
            System.out.println("Transaction is aborted");
        }

    }
}

