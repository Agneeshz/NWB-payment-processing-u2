package com.ezpay.payment.repository;

import com.ezpay.payment.model.UPI;
//import com.ezpay.payment.service.UPIService;

public class UPIRepository 
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

    public static String[] custUPIID = {"adithya@oksbi", "agneesh@oksbi", "deepak@oksbi", "aishveen@oksbi", "hasini@oksbi"};
    public static UPI[] customer = {customer1, customer2, customer3, customer4, customer5};

    static public void updateDetails(String upiId, int amount, UPI customer)
    {
        //from list get person details using upiId for searching 
        //update the array by substracting the amount
        customer.setBalance(amount);
        
        //create a list for each user using his upiid
        //in this list record the transaction
    }

}
