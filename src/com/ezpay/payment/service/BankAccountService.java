/**
 * Module Name: BankAccountService
 * 
 * Description: This module provides services related to bank account transactions. It verifies account details 
 * and ensures that a transaction can be completed based on the account balance. If the balance is sufficient, 
 * the transaction is processed and the account balance is updated.
 * 
 * Author:
 * Deepak Reddy
 * 
 * Date: August 10,2024
 */


package com.ezpay.payment.service;
import com.ezpay.payment.repository.BankAccountRepository;

public class BankAccountService 
{
	//Verifies the bank account details and processes the transaction if the balance is sufficient.
    static public void verifyDetails(String accountNumber, int amount) 
    {   
        String custAccountNumber = "null";
        int j=0;
        
        // Search for the account number in the repository
        for(int i=0; i<5; i++)
        {
            if(accountNumber.equalsIgnoreCase(BankAccountRepository.custBankAccountNumber[i]))
            {
                custAccountNumber = accountNumber;
                j=i;
            }
        }
        
        // Check if the account number was found
        if(custAccountNumber != "null")
        {            
            int balance = BankAccountRepository.customer[j].getBalance();
            
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
                BankAccountRepository.updateDetails(accountNumber, remainingAmount, BankAccountRepository.customer[j]);
            }
        }
        else
        {
            System.out.println("Invalid Account Number");
        }
    }
        
}