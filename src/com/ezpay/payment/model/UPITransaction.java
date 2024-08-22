package com.ezpay.payment.model;

import java.util.Date;

public class UPITransaction {
    private String senderUpiId;
    private String receiverUpiId;
    private double amount;
    private Date date;
    private String note;
    private String status;

	
    	public String getSenderUpiId() {
		return senderUpiId;
	}

	public void setSenderUpiId(String senderUpiId) {
		this.senderUpiId = senderUpiId;
	}

	public String getReceiverUpiId() {
		return receiverUpiId;
	}

	public void setReceiverUpiId(String receiverUpiId) {
		this.receiverUpiId = receiverUpiId;
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
