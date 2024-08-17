package Model;

import java.util.Date;

public class BankTransaction {

    private String senderAccountNumber;
    private String receiverAccountNumber;
    private String ifscCode;
    private double amount;
    private Date date;
    private String note;
    private String status;

    public BankTransaction(String senderAccountNumber, String ifscCode, String receiverAccountNumber, double amount, Date date, String note, String status) {
        this.senderAccountNumber = senderAccountNumber;
        this.receiverAccountNumber = receiverAccountNumber;
        this.ifscCode = ifscCode;
        this.amount = amount;
        this.date = date;
        this.note = note;
        this.status = status;
    }

    // Getters and toString method
    public String toString() {

        return "Transaction of " + amount + " from ACCOUNT NUMBER : " + senderAccountNumber + " IFSC CODE : " + ifscCode + " To ACCOUNT NUMBER :" + receiverAccountNumber +
                " on " + date + ". Status: " + status + ". Note: " + note;
    }

}
