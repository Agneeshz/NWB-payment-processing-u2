package Model;

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





    

