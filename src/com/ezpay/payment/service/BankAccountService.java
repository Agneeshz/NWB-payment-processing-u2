package com.ezpay.payment.service;
import com.ezpay.payment.model.BankAccount;
import com.ezpay.payment.model.UPI;
import com.ezpay.payment.repository.BankAccountRepository;

public class BankAccountService 
{
    static public void verifyDetails(String accountNumber, int amount) 
    {   
        String custAccountNumber = "null";
        int j=0;

        for(int i=0; i<5; i++)
        {
            if(accountNumber.equalsIgnoreCase(BankAccountRepository.custBankAccountNumber[i]))
            {
                custAccountNumber = accountNumber;
                j=i;
            }
        }
        if(custAccountNumber != "null")
        {            
            int balance = BankAccountRepository.customer[j].getBalance();

            if(amount > balance)
            {
                System.out.println("Insufficient balance");
            }

            else
            {
                System.out.println("Transaction Successful");
                int remainingAmount = balance-amount;
                BankAccountRepository.updateDetails(accountNumber, remainingAmount, BankAccountRepository.customer[j]);
            }
        }
        else
        {
            System.out.println("Invalid Account Number");
        }
    }
        
}