package com.ezpay.payment.Test;

import com.ezpay.payment.Model.Transaction;
import com.ezpay.payment.Repo.TransactionRepository;
import com.ezpay.payment.Repo.UserRepository;
import com.ezpay.payment.Service.UPIService;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.beans.Transient;

public class UPIServiceTest {

        UserRepository userRepository = new UserRepository();
        TransactionRepository transactionRepository = new TransactionRepository();

        @Before
        public void setUp() {
            UPIService upiService = new UPIService(userRepository, transactionRepository);
        }
        
        // Test valid transaction
        @Test
        public void testValidTransaction(){
        String result = upiService.processPayment("adithya@oksbi", "agneesh@oksbi", 1000, "Payment for dinner");
        assertEquals("Transaction Successful.", result);
        }

        // Test invalid UPI ID
        @Test
        public void testInvalidUpiId(){
        result = upiService.processPayment("invalid@upi", "deepak@oksbi", 1000, "");
        assertEquals("Invalid UPI ID", result);
        }

        // Test insufficient funds
        @Test 
        public void testInsufficientFunds(){
        result = upiService.processPayment("aishveen@oksbi", "hasini@oksbi", 100000, "Payment for car");
        assertEquals("Error: Insufficient funds.", result);
        }
    
}
