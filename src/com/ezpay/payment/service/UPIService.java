/**
 * Module Name: UPIService
 * 
 * Description: This module provides services related to UPI transactions. It verifies UPI details and ensures 
 * that a transaction can be completed based on the user's balance. If the balance is sufficient, the transaction 
 * is processed and the UPI balance is updated.
 * 
 * Author:
 * Adithya Mode
 * 
 * Date: August 10,2024
 */


package com.ezpay.payment.service;
import com.ezpay.payment.repository.UPIRepository;


public class UPIService 
{
    //Verifies details and processes the transaction if the balance is sufficient.
    static public void verifyDetails(String upiId, int amount) 
    {   
        String custUpiId = "null";
        int j=0;
        
        // Search for the UPI ID in the repository
        for(int i=0; i<5; i++)
        {
            if(upiId.equalsIgnoreCase(UPIRepository.custUPIID[i]))
            {
                custUpiId = upiId;
                j=i;
            }
        }
        
        // Check if the UPI ID was found
        if(custUpiId != "null")
        {            
            int balance = UPIRepository.customer[j].getBalance();
            
            // Check if the account has sufficient balance
            if(amount > balance)
            {
                System.out.println("Insufficient balance");
            }

            else
            {
                System.out.println("Transaction Successful");
                int remainingAmount = balance-amount;
                
                // Update the customer's account balance
                UPIRepository.updateDetails(upiId, remainingAmount, UPIRepository.customer[j]);
            }
        }

        else
        {
            System.out.println("Invalid UPI ID");
        }
    }
    
}
