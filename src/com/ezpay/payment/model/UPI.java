/**
 * Module Name: UPI
 * 
 * Description: This module represents a UPI (Unified Payments Interface) entity within the payment system. 
 * It stores and manages information related to a customer's bank account, such as UPI ID, customer name, 
 * balance, mobile number, and email. The module provides functionality to access and update these details 
 * as required, and it can be used in operations such as verifying account details, checking balance, and 
 * updating customer information.
 * 
 * Author:
 * Agneesh Dasgupta
 * 
 * Date: August 23, 2024
 */

package com.ezpay.payment.model;

public class UPI 
{
    private String upiId;         // UPI ID associated with the customer's account
    private String custName;      // Name of the customer
    private double balance;       // Account balance for the UPI ID
    private long mobileNumber;    // Registered mobile number of the customer
    private String email;         // Email address of the customer
    
    // Constructor to initialize UPI entity with the provided details
    public UPI(String custName, String upiId, double balance, long mobileNumber, String email)
    {
        this.upiId = upiId;
        this.custName = custName;
        this.balance = balance;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }
    
    // Getter and setter methods for UPI ID
    public String getUpiId() 
    {
		return upiId;
	}

    public void setUpiId(String upiId) 
    {
		this.upiId = upiId;
	}

    // Getter and setter methods for customer name
    public String getCustName()
    {
        return custName;
    }

    public void setCustName(String custName)
    {
        this.custName = custName;
    }
    
    // Getter and setter methods for account balance
    public double getBalance()
    {
        return balance;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    // Getter and setter methods for mobile number
    public long getMobileNumber()
    {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber)
    {
        this.mobileNumber = mobileNumber;
    }

    // Getter and setter methods for email address
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
