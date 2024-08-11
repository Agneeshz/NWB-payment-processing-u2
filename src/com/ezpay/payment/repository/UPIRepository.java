package com.ezpay.payment.repository;

import com.ezpay.payment.model.UPI;
//import com.ezpay.payment.service.UPIService;

public class UPIRepository 
{

    static public void updateDetails(String upiId, int amount, UPI customer)
    {
        //from list get person details using upiId for searching 
        //update the array by substracting the amount
        customer.setBalance(amount);
        
        //create a list for each user using his upiid
        //in this list record the transaction
    }

}
