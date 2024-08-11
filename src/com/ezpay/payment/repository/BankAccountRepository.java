package com.ezpay.payment.repository;

import com.ezpay.payment.model.BankAccount;
//import com.ezpay.payment.service.UPIService;

public class BankAccountRepository 
{

    static public void updateDetails(String accountNumber, int amount, BankAccount customer)
    {
        //from list get person details using account number for searching 
        //update the array by substracting the amount
        customer.setBalance(amount);
        
        //create a list for each user using his account number
        //in this list record the transaction
    }

}