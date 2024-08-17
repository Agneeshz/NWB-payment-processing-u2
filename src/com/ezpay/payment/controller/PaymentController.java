package com.ezpay.payment.Controller;

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
