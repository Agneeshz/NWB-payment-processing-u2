package com.ezpay.payment.model;

import java.util.Date;

public class UPITransaction {
    private String senderUpiId;
    private String receiverUpiId;
    private double amount;
    private Date date;
    private String note;
    private String status;

    public UPITransaction(String senderUpiId, String receiverUpiId, double amount, Date date, String note, String status) {
        this.senderUpiId = senderUpiId;
        this.receiverUpiId = receiverUpiId;
        this.amount = amount;
        this.date = date;
        this.note = note;
        this.status = status;
    }

    // Getters and toString method
    public String toString() {
        return "Transaction from " + senderUpiId + " to " + receiverUpiId +
               " of amount " + amount + " on " + date +
               ". Status: " + status + ". Note: " + note;
    }
}