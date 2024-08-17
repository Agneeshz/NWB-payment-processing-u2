package com.ezpay.payment.Repo;

import com.ezpay.payment.Model.BankUser;
import java.util.HashMap;
import java.util.Map;

public class BankUserRepository {

    private Map<String, BankUser> bankusers = new HashMap<>();

    public BankUserRepository() {
        // Initializing with some sample users
        bankusers.put("99887744556", new BankUser("Adithya Mode", "99887744556", "SBIN096321",  5000));
        bankusers.put("88997755664", new BankUser("Agneesh Dasgupta", "88997755664", "SBIN098125" ,8000));
        bankusers.put("88995566447", new BankUser("Deepak Reddy", "88995566447", "SBIN096325" ,10000));
        bankusers.put("88662211335", new BankUser("Aishveen Kaur", "88662211335","SBIN098512" , 12000));
        bankusers.put("88997755661", new BankUser("Hasini", "88997755661","SBIN098745" , 15000));
    }

    public BankUser findUserByAccountNumber(String accountNumber) {
        return bankusers.get(accountNumber);
    }

    
}
