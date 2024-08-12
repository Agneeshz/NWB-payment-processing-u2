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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import com.ezpay.payment.repository.BankAccountRepository;
import com.ezpay.payment.service.BankAccountService;

public class BankAccountServiceTest {

    // Create a new instance of UPIService before each test
    private BankAccountService bankAccountService;
    private BankAccountRepository bankAccountRepositoty;
    @Before
    public void setUp() {
        bankAccountService = new BankAccountService();
    }
    @Test
    public void testVerifyDetails_SuccessfulTransaction() {
        String accountNumber = "30467251894";
        int amount = 1000;
        
        // Perform the transaction
        bankAccountService.verifyDetails(accountNumber, amount);

        // Verify the balance has been updated
        assertEquals(4000, bankAccountRepositoty.customer1.getBalance());
    }
    @Test
    public void testVerifyDetails_InsufficientBalance() {
        String accountNumber = "30467251894";
        int amount = 6000;
        
        // Capture output to verify the correct message is printed
        // Use ByteArrayOutputStream to capture System.out
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Perform the transaction
        bankAccountService.verifyDetails(accountNumber, amount);

        // Restore System.out
        System.setOut(originalOut);

        String output = outputStream.toString().trim();
        assertTrue(output.contains("Insufficient balance"));
    }
    @Test
    public void testVerifyDetails_InvalidAccountNumber() {
        String accountNumber = "30467851894";
        int amount = 1000;
        
        // Capture output to verify the correct message is printed
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Perform the transaction
        bankAccountService.verifyDetails(accountNumber, amount);

        // Restore System.out
        System.setOut(originalOut);

        String output = outputStream.toString().trim();
        assertTrue(output.contains("Invalid Account Number"));
    }
    @Test
    public void testVerifyDetails_ValidTransaction() {
        String accountNumber = "14932850746";
        int amount = 500;
        
        // Perform the transaction
        bankAccountService.verifyDetails(accountNumber, amount);

        // Verify the balance has been updated
        assertEquals(6500, bankAccountRepositoty.customer5.getBalance());
    }
}