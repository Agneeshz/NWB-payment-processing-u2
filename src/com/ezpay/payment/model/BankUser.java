/**
 * Module Name: BankAccount
 * 
 * Description:This module represents a bank account entity within the payment system to store and manage 
 * information related to a customer's bank account. It can be used to verify account details, check balance,
 * and update information as required.
 * 
 * Author:
 * Deepak Reddy
 * 
 * Date: August 10,2024
 */

package com.ezpay.payment.model;

public class BankUser {

    private String accountNumber;
    private String ifscCode;
    private String custName;
    private double balance;

    public BankUser(String custName, String accountNumber, String ifscCode, double balance) {
        this.custName = custName;
        this.accountNumber = accountNumber;
        this.ifscCode = ifscCode;
        this.balance = balance;
    }

    //setters and getters

    public String getCustName() {
        return custName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}