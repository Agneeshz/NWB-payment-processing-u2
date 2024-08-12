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

public class PaymentController 
{
    //Assuming user has already logged in
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) 
    {
        System.out.println("Please enter the mode of payment");
        System.out.println("UPI or Bank Transfer ?");
        String modeOfPayment = scanner.nextLine();


        if(modeOfPayment.equalsIgnoreCase("UPI"))
        {
            UPIDetails.getUPIDetails();
        }

        else if(modeOfPayment.equalsIgnoreCase("Bank Transfer"))
        {
        	BankAccountDetails.getBankAccountDetails();
        }
		
     }
}
