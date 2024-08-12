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
import com.ezpay.payment.service.UPIService;
import com.ezpay.payment.repository.UPIRepository;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UPIServiceTest {

    // Create a new instance of UPIService before each test
    private UPIService upiService;
    private UPIRepository upiRepository;

    @Before
    public void setUp() {
        upiService = new UPIService();
    }

    @Test
    public void testVerifyDetails_SuccessfulTransaction() {
        String upiId = "adithya@oksbi";
        int amount = 1000;
        
        // Perform the transaction
        upiService.verifyDetails(upiId, amount);

        // Verify the balance has been updated
        assertEquals(4000, upiRepository.customer1.getBalance());
    }

    @Test
    public void testVerifyDetails_InsufficientBalance() {
        String upiId = "adithya@oksbi";
        int amount = 6000;
        
        // Capture output to verify the correct message is printed
        // Use ByteArrayOutputStream to capture System.out
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Perform the transaction
        upiService.verifyDetails(upiId, amount);

        // Restore System.out
        System.setOut(originalOut);

        String output = outputStream.toString().trim();
        assertTrue(output.contains("Insufficient balance"));
    }

    @Test
    public void testVerifyDetails_InvalidUPIId() {
        String upiId = "invalid@upi";
        int amount = 1000;
        
        // Capture output to verify the correct message is printed
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Perform the transaction
        upiService.verifyDetails(upiId, amount);

        // Restore System.out
        System.setOut(originalOut);

        String output = outputStream.toString().trim();
        assertTrue(output.contains("Invalid UPI ID"));
    }

    @Test
    public void testVerifyDetails_ValidTransaction() {
        String upiId = "hasini@oksbi";
        int amount = 500;
        
        // Perform the transaction
        upiService.verifyDetails(upiId, amount);

        // Verify the balance has been updated
        assertEquals(6500, upiRepository.customer5.getBalance());
    }
}