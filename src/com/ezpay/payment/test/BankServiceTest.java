package com.ezpay.payment.Test;

import com.ezpay.payment.Model.BankTransaction;
import com.ezpay.payment.Repo.BankTransactionRepository;
import com.ezpay.payment.Repo.BankUserRepository;
import com.ezpay.payment.Service.BankService;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.beans.Transient;

import org.junit.Before;
import org.junit.Test;

public class BankServiceTest {
    
        BankUserRepository bankUserRepository = new BankUserRepository();
        BankTransactionRepository bankTransactionRepository = new BankTransactionRepository();

        @Before
        public void setUp() {
            BankService bankService = new BankService(bankUserRepository, bankTransactionRepository);
        }
        
        
        // Test valid transaction
        @Test 
        public void testValidTransaction(){
        String result = bankService.processPayment("99887744556", "SBIN096321", "88997755664", 1000, "Payment for dinner");
        System.out.println(result);
        }

        // Test invalid UPI ID
        @Test 
        public void testInvalidUpiId(){
        result = bankService.processPayment("99662211523", "SBIN096321", "99887744556", 1000, "");
        System.out.println(result);
        }

        // Test insufficient funds
        @Test 
        public void testInsufficientFunds(){
        result = bankService.processPayment("88662211335", "SBIN098512", "88997755661", 100000, "Payment for car");
        System.out.println(result);
        }
    
}
