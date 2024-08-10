package com.ezpay.payment.service;

import com.ezpay.payment.model.UPI;
import com.ezpay.payment.repository.UPIRepository;


public class UPIService 
{
    public static UPI customer1 = new UPI("adithya@oksbi","adithya", 5000, 8105206202L, "adithya@gmail.com");
    public static UPI customer2 = new UPI("agneesh@oksbi","agneesh", 10000, 9563214852L, "agneesh@gmail.com");
    public static UPI customer3 = new UPI("deepak@oksbi","deepak", 8000, 8105206202L, "deepak@gmail.com");
    public static UPI customer4 = new UPI("aishveen@oksbi","aishveen", 9000, 8105206202L, "aishveen@gmail.com");
    public static UPI customer5 = new UPI("hasini@oksbi","hasini", 7000, 8105206202L, "hasini@gmail.com");

    /*ArrayList<String> custList = new ArrayList<>();

    custList.add(customer1.getUpiId());
    custList.add(customer2.getUpiId());
    custList.add(customer3.getUpiId());
    custList.add(customer4.getUpiId());
    custList.add(customer5.getUpiId());*/

    static String[] custUPIID = {"adithya@oksbi", "agneesh@oksbi", "deepak@oksbi", "aishveen@oksbi", "hasini@oksbi"};
    static UPI[] customer = {customer1, customer2, customer3, customer4, customer5};

    //verifydetails
    static public void verifyDetails(String upiId, int amount) 
    {   
        String custUpiId = "null";
        int j=0;

        for(int i=0; i<5; i++)
        {
            if(upiId.equalsIgnoreCase(custUPIID[i]))
            {
                custUpiId = upiId;
                j=i;
            }
        }

        if(custUpiId != "null")
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
                UPIRepository.updateDetails(upiId, remainingAmount, customer[j]);
            }
        }

        else
        {
            System.out.println("Invalid UPI ID");
        }
    }
    
}
