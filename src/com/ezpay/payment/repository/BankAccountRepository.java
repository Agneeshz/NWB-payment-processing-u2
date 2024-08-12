/**
 * Module Name: BankAccountRepository
 * 
 * Description: This module serves as the repository for storing and managing bank account information.
 * It contains a list of predefined bank accounts and provides functionality to update account details such as
 * balance after a transaction.
 * 
 * Author:
 * Deepak Reddy
 * 
 * Date: August 10,2024
 */

package com.ezpay.payment.repository;

import com.ezpay.payment.model.BankAccount;
//import com.ezpay.payment.service.UPIService;

public class BankAccountRepository 
{
    public static BankAccount customer1 = new BankAccount("30467251894","SBIN0004592", 5000, "adithya");
    public static BankAccount customer2 = new BankAccount("29834710562","SBIN0008374", 10000, "agneesh");
    public static BankAccount customer3 = new BankAccount("31945728601","SBIN0012368", 8000, "deepak");
    public static BankAccount customer4 = new BankAccount("23017694825","SBIN0019457", 9000, "aishveen");
    public static BankAccount customer5 = new BankAccount("14932850746","SBIN0006738", 7000, "hasini");
    
    public static String[] custBankAccountNumber = {"30467251894", "29834710562", "31945728601", "23017694825", "14932850746"};
    public static BankAccount[] customer = {customer1, customer2, customer3, customer4, customer5};
    
    static public void updateDetails(String accountNumber, int amount, BankAccount customer)
    {
        //from list get person details using account number for searching 
        //update the array by substracting the amount
        customer.setBalance(amount);
        
        //create a list for each user using his account number
        //in this list record the transaction
    }

}