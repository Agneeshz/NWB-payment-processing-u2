package com.ezpay.payment.service;
import com.ezpay.payment.model.BankAccount;
import com.ezpay.payment.model.UPI;
import com.ezpay.payment.repository.BankAccountRepository;

public class BankAccountService 
{
    public static BankAccount customer1 = new BankAccount("30467251894","SBIN0004592", 5000, "adithya");
    public static BankAccount customer2 = new BankAccount("29834710562","SBIN0008374", 10000, "agneesh");
    public static BankAccount customer3 = new BankAccount("31945728601","SBIN0012368", 8000, "deepak");
    public static BankAccount customer4 = new BankAccount("23017694825","SBIN0019457", 9000, "aishveen");
    public static BankAccount customer5 = new BankAccount("14932850746","SBIN0006738", 7000, "hasini");
    
    static String[] custBankAccountNumber = {"30467251894", "29834710562", "31945728601", "23017694825", "14932850746"};
    static BankAccount[] customer = {customer1, customer2, customer3, customer4, customer5};
    
    static public void verifyDetails(String accountNumber, int amount) 
    {   
        String custAccountNumber = "null";
        int j=0;

        for(int i=0; i<5; i++)
        {
            if(accountNumber.equalsIgnoreCase(custBankAccountNumber[i]))
            {
                custAccountNumber = accountNumber;
                j=i;
            }
        }
        if(custAccountNumber != "null")
        {            
            int balance = customer[j].getBalance();

            if(amount > balance)
            {
                System.out.println("Insufficient balance");
            }

            else
            {
                System.out.println("Transaction Successful");
                int remainingAmount = balance-amount;
                BankAccountRepository.updateDetails(accountNumber, remainingAmount, customer[j]);
            }
        }
        else
        {
            System.out.println("Invalid Account Number");
        }
    }
        
}