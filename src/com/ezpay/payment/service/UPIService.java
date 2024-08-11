package com.ezpay.payment.service;

import com.ezpay.payment.model.UPI;
import com.ezpay.payment.repository.UPIRepository;


public class UPIService 
{
    //verify details
    static public void verifyDetails(String upiId, int amount) 
    {   
        String custUpiId = "null";
        int j=0;

        for(int i=0; i<5; i++)
        {
            if(upiId.equalsIgnoreCase(UPIRepository.custUPIID[i]))
            {
                custUpiId = upiId;
                j=i;
            }
        }

        if(custUpiId != "null")
        {            
            int balance = UPIRepository.customer[j].getBalance();

            if(amount > balance)
            {
                System.out.println("Insufficient balance");
            }

            else
            {
                System.out.println("Transaction Successful");
                int remainingAmount = balance-amount;
                UPIRepository.updateDetails(upiId, remainingAmount, UPIRepository.customer[j]);
            }
        }

        else
        {
            System.out.println("Invalid UPI ID");
        }
    }
    
}
