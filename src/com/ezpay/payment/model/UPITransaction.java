/**
 * Module Name: UPITransaction
 * 
 * Description: This module represents a UPI transaction within the payment system. 
 * It captures the details of a transaction, including the sender's and receiver's UPI IDs, 
 * the transaction amount, the date of the transaction, an optional note, and the transaction status. 
 * This class provides the necessary getters and setters to manage transaction data 
 * and includes a `toString` method for easy representation of transaction details.
 * 
 * Author:
 * Agneesh Dasgupta
 * 
 * Date: August 23, 2024
 */

package com.ezpay.payment.model;

import java.util.Date;

public class UPITransaction {
    private String senderUpiId;    // UPI ID of the sender
    private String receiverUpiId;  // UPI ID of the receiver
    private double amount;         // Transaction amount
    private Date date;             // Date of the transaction
    private String note;           // Optional note associated with the transaction
    private String status;         // Status of the transaction (e.g., Success, Failed)

    // Constructor to initialize the UPITransaction with provided details
    public UPITransaction(String senderUpiId, String receiverUpiId, double amount, Date date, String note, String status) {
        this.senderUpiId = senderUpiId;
        this.receiverUpiId = receiverUpiId;
        this.amount = amount;
        this.date = date;
        this.note = note;
        this.status = status;
    }

    // Getter and setter methods for sender UPI ID
    public String getSenderUpiId() {
	return senderUpiId;
    }

    public void setSenderUpiId(String senderUpiId) {
	this.senderUpiId = senderUpiId;
    }

    // Getter and setter methods for receiver UPI ID
    public String getReceiverUpiId() {
	return receiverUpiId;
    }

    public void setReceiverUpiId(String receiverUpiId) {
	this.receiverUpiId = receiverUpiId;
    }

    // Getter and setter methods for transaction amount
    public double getAmount() {
	return amount;
    }

    public void setAmount(double amount) {
	this.amount = amount;
    }

    // Getter and setter methods for transaction date
    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    // Getter and setter methods for transaction note
    public String getNote() {
	return note;
    }

    public void setNote(String note) {
	this.note = note;
    }

    // Getter and setter methods for transaction status
    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    // toString method to represent transaction details as a string
    public String toString() {
        return "Transaction from " + senderUpiId + " to " + receiverUpiId +
               " of amount " + amount + " on " + date +
               ". Status: " + status + ". Note: " + note;
    }
}
