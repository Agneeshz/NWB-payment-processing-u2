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

public class BankAccount{
	private String accountNumber;
	private String ifscCode;
	private int balance;
	private String custName;
	
	//Constructor to initialize a bank account transaction with the provided details.
	public BankAccount(String accountNumber, String ifscCode, int balance, String custName) {
		this.accountNumber = accountNumber;
		this.ifscCode = ifscCode;
		this.balance = balance;
		this.custName = custName;
	}
	
	//getters and setters for all the params
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

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}
	
}