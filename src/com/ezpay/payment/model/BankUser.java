/**
 * Module Name: BankUser
 *
 * Description: This module represents a bank user in the payment system. 
 * The class provides getter and setter methods for accessing and modifying these attributes, 
 * facilitating user management within the banking application.
 * 
 * Author:
 * Hasini Sai Ramya
 * 
 * Date: August 20, 2024
 * 
 */


package com.ezpay.payment.model;

public class BankUser {
    private String custName;
    private String accountNumber;
    private String ifscCode;
    private double balance;

    public BankUser(String custName, String accountNumber, String ifscCode, double balance) {
        this.custName = custName;
        this.accountNumber = accountNumber;
        this.ifscCode = ifscCode;
        this.balance = balance;
    }

    // Getters and Setters
    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
