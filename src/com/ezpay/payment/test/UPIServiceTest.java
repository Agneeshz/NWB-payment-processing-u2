package com.ezpay.payment.test;

import com.ezpay.payment.repository.UPIRepository;
import com.ezpay.payment.repository.UPITransactionRepository;
import com.ezpay.payment.service.UPIService;
import com.ezpay.payment.util.DBConnection;

import static org.junit.Assert.*;
import java.sql.*;
import org.junit.*;

public class UPIServiceTest {

    private static Connection connection;
    private UPIService upiService;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database");
        }
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Before
    public void setUp() throws Exception {
        UPIRepository userRepository = new UPIRepository(connection);
        UPITransactionRepository transactionRepository = new UPITransactionRepository(connection);
        upiService = new UPIService(userRepository, transactionRepository);
        connection.setAutoCommit(false);
    }

    @After
    public void tearDown() throws Exception {
        connection.rollback();
    }

    @Test
    public void testValidTransaction() {
        String result = upiService.processPayment("adithya@oksbi", "agneesh@oksbi", 1000, "Payment for dinner");
        assertEquals("Transaction Successful.", result);
    }

    @Test
    public void testInvalidUpiId() {
        String result = upiService.processPayment("invalid@upi", "deepak@oksbi", 1000, "");
        assertEquals("Invalid UPI ID", result);
    }

    @Test
    public void testInsufficientFunds() {
        String result = upiService.processPayment("aishveen@oksbi", "hasini@oksbi", 100000, "Payment for car");
        assertEquals("Error: Insufficient funds.", result);
    }

    @Test
    public void testZeroAmountTransaction() {
        String result = upiService.processPayment("adithya@oksbi", "agneesh@oksbi", 0, "Zero amount test");
        assertEquals("Error: Transaction amount must be greater than zero.", result);
    }

    @Test
    public void testSelfPayment() {
        String result = upiService.processPayment("adithya@oksbi", "adithya@oksbi", 1000, "Self payment test");
        assertEquals("Error: Cannot transfer money to the same UPI ID.", result);
    }

    @Test
    public void testNegativeAmountTransaction() {
        String result = upiService.processPayment("adithya@oksbi", "agneesh@oksbi", -1000, "Negative amount test");
        assertEquals("Error: Transaction amount cannot be negative.", result);
    }

    @Test
    public void testLargeTransaction() {
        String result = upiService.processPayment("deepak@oksbi", "hasini@oksbi", Integer.MAX_VALUE, "Large transaction test");
        assertEquals("Error: Insufficient funds.", result);
    }
}
