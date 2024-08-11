package com.ezpay.payment.model;

public class BankAccount{
	private String accountNumber;
	private String ifscCode;
	private int balance;
	private String custName;
	
	public BankAccount(String accountNumber, String ifscCode, int balance, String custName) {
		this.accountNumber = accountNumber;
		this.ifscCode = ifscCode;
		this.balance = balance;
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