package com.ezpay.payment.model;

import java.util.Date;

public class BankTransaction {
    private String senderAccountNumber;
    private String ifscCode;
    private String receiverAccountNumber;
    private double amount;
    private Date date;
    private String note;
    private String status;

    public BankTransaction(String senderAccountNumber, String ifscCode, String receiverAccountNumber, double amount, Date date, String note, String status) {
        this.senderAccountNumber = senderAccountNumber;
        this.ifscCode = ifscCode;
        this.receiverAccountNumber = receiverAccountNumber;
        this.amount = amount;
        this.date = date;
        this.note = note;
        this.status = status;
    }

    // Getters and Setters
    public String getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public void setSenderAccountNumber(String senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    public void setReceiverAccountNumber(String receiverAccountNumber) {
        this.receiverAccountNumber = receiverAccountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
