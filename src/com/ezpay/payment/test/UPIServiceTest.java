package com.ezpay.payment.test;
import com.ezpay.payment.service.UPIService;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UPIServiceTest {

    // Create a new instance of UPIService before each test
    private UPIService upiService;

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
        assertEquals(4000, upiService.customer1.getBalance());
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
        assertEquals(6500, upiService.customer5.getBalance());
    }
}