package Model;

public class User {
    private String name;
    private String upiId;
    private double balance;

    public User(String name, String upiId, double balance) {
        this.name = name;
        this.upiId = upiId;
        this.balance = balance;
    }

    //setters and getters

    public String getName() {
        return name;
    }

    public String getUpiId() {
        return upiId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
