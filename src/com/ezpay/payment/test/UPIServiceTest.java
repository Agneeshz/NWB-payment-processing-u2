/**
 * Module Name: UPIServiceTest
 *
 * Description:This module tests the functionality of the UPIService class, ensuring that UPI transactions
 * are processed correctly based on various conditions like sufficient balance, invalid UPI IDs, etc.
 * 
 * Author:
 * Agneesh Dasgputa
 * 
 * Date: August 10,2024
 * */

package com.ezpay.payment.test;

import com.ezpay.payment.repository.UPITransactionRepository;
import com.ezpay.payment.repository.UPIRepository;
import com.ezpay.payment.service.UPIService;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UPIServiceTest {

        private UPIService upiservice;

        @Before
        public void setUp() {
        	UPIRepository userRepository = new UPIRepository();
            UPITransactionRepository transactionRepository = new UPITransactionRepository();
            UPIService upiService = new UPIService(userRepository, transactionRepository);
        }
        
        // Test valid transaction
        @Test
        public void testValidTransaction(){
        String result = UPIService.processPayment("adithya@oksbi", "agneesh@oksbi", 1000, "Payment for dinner");
        assertEquals("Transaction Successful.", result);
        }

        // Test invalid UPI ID
        @Test
        public void testInvalidUpiId(){
        String result = UPIService.processPayment("invalid@upi", "deepak@oksbi", 1000, "");
        assertEquals("Invalid UPI ID", result);
        }

        // Test insufficient funds
        @Test 
        public void testInsufficientFunds(){
        String result = UPIService.processPayment("aishveen@oksbi", "hasini@oksbi", 100000, "Payment for car");
        assertEquals("Error: Insufficient funds.", result);
        }
    
}