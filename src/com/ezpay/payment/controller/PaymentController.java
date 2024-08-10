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
            //call the bank transfer details class
        }
		
	}
}
