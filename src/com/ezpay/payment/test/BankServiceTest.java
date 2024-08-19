/**
 * Module Name: BankAccountServiceTest
 *
 * Description:This module tests the functionality of the BankAccountService class, ensuring that bank account
 * transactions are processed correctly based on various conditions like sufficient balance, invalid account 
 * numbers, etc.
 * 
 * Author:
 * Aishveen Kaur
 * 
 * Date: August 10, 2024
 * */

package com.ezpay.payment.test;

import com.ezpay.payment.repository.BankTransactionRepository;
import com.ezpay.payment.repository.BankUserRepository;
import com.ezpay.payment.service.BankService;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class BankServiceTest {
    
	private BankService bankService;

    @Before
    public void setUp() {
        BankUserRepository bankUserRepository = new BankUserRepository();
        BankTransactionRepository bankTransactionRepository = new BankTransactionRepository();
        bankService = new BankService(bankUserRepository, bankTransactionRepository);
    }
        
        
        // Test valid transaction
        @Test 
        public void testValidTransaction(){
        String result = bankService.processPayment("99887744556", "SBIN096321", "88997755664", 1000, "Payment for dinner");
        assertEquals("Transaction Successful.", result);
        }

        // Test invalid account number
        @Test 
        public void testInvalidAccountNumber(){
        String result = bankService.processPayment("99662211523", "SBIN096321", "99887744556", 1000, "");
        assertEquals("Invalid Account Number", result);
        }

        // Test insufficient funds
        @Test 
        public void testInsufficientFunds(){
        String result = bankService.processPayment("88662211335", "SBIN098512", "88997755661", 100000, "Payment for car");
        assertEquals("Error: Insufficient funds.", result);
        }
    
}