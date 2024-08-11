package com.ezpay.payment.model;

public class UPI 
{
    private String upiId;
    private String custName;
    private int balance;
    private long mobileNumber;
    private String email;

    public UPI(String upiId, String custName, int balance, long mobileNumber, String email)
    {
        this.upiId = upiId;
        this.custName = custName;
        this.balance = balance;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }

    public String getUpiId() 
    {
		return upiId;
	}

    public void setUpiId(String upiId) 
    {
		this.upiId = upiId;
	}

    public String getCustName()
    {
        return custName;
    }

    public void setCustName(String custName)
    {
        this.custName = custName;
    }
    
    public int getBalance()
    {
        return balance;
    }

    public void setBalance(int balance)
    {
        this.balance = balance;
    }

    public long getMobileNumber()
    {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber)
    {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

}
