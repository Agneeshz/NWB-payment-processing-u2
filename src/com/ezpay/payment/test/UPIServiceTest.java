package com.ezpay.payment.test;

import com.ezpay.payment.repository.UPIRepository;
import com.ezpay.payment.repository.UPITransactionRepository;
import com.ezpay.payment.service.UPIService;
import com.ezpay.payment.util.DBConnection;

import static org.junit.Assert.*;
import java.sql.*;
import org.junit.*;

public class UPIServiceTest {

    private static Connection connection;  // Database connection
    private UPIService upiService;         // Instance of UPIService to be tested

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        // Set up the database connection before any test cases run
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database");
        }
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        // Close the database connection after all test cases have run
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Before
    public void setUp() throws Exception {
        // Initialize UPIService and start a transaction before each test
        UPIRepository userRepository = new UPIRepository(connection);
        UPITransactionRepository transactionRepository = new UPITransactionRepository(connection);
        upiService = new UPIService(userRepository, transactionRepository);
        connection.setAutoCommit(false);
    }

    @After
    public void tearDown() throws Exception {
        // Roll back the transaction after each test to maintain database state
        connection.rollback();
    }

    @Test
    public void testValidTransaction() {
        // Test for a valid UPI transaction
        String result = upiService.processPayment("agneesh@oksbi", "deepak@oksbi", 1000, "Payment for dinner");
        assertEquals("Transaction Successful.", result);
    }

    @Test
    public void testInvalidUpiId() {
        // Test for an invalid UPI ID
        String result = upiService.processPayment("invalid@upi", "deepak@oksbi", 1000, "");
        assertEquals("Invalid UPI ID", result);
    }

    @Test
    public void testInsufficientFunds() {
        // Test for a transaction with insufficient funds
        String result = upiService.processPayment("aishveen@oksbi", "hasini@oksbi", 100000, "Payment for car");
        assertEquals("Error: Insufficient funds.", result);
    }

    @Test
    public void testZeroAmountTransaction() {
        // Test for a transaction with zero amount
        String result = upiService.processPayment("adithya@oksbi", "agneesh@oksbi", 0, "Zero amount test");
        assertEquals("Error: Transaction amount must be greater than zero.", result);
    }

    @Test
    public void testSelfPayment() {
        // Test for a self-payment transaction (sending to the same UPI ID)
        String result = upiService.processPayment("adithya@oksbi", "adithya@oksbi", 1000, "Self payment test");
        assertEquals("Error: Cannot transfer money to the same UPI ID.", result);
    }

    @Test
    public void testNegativeAmountTransaction() {
        // Test for a transaction with a negative amount
        String result = upiService.processPayment("adithya@oksbi", "agneesh@oksbi", -1000, "Negative amount test");
        assertEquals("Error: Transaction amount cannot be negative.", result);
    }

    @Test
    public void testLargeTransaction() {
        // Test for a large transaction amount exceeding available balance
        String result = upiService.processPayment("deepak@oksbi", "hasini@oksbi", Integer.MAX_VALUE, "Large transaction test");
        assertEquals("Error: Insufficient funds.", result);
    }

    @Test
    public void testNullSenderUpiId() {
        // Test for a null sender UPI ID
        String result = upiService.processPayment(null, "agneesh@oksbi", 1000, "Null sender UPI ID test");
        assertEquals("Error: UPI ID cannot be null.", result);
    }
    
    @Test
    public void testNullReceiverUpiId() {
    	// Test for a null receiver UPI ID
        String result = upiService.processPayment("deepak@oksbi", null, 1000, "Null receiver UPI ID test");
        assertEquals("Error: UPI ID cannot be null.", result);
    }

    @Test
    public void testTransactionHistory() throws SQLException {
        // Test to verify if a transaction is recorded in the transaction history
        upiService.processPayment("deepak@oksbi", "agneesh@oksbi", 1000, "Payment for test history");
        
        // Query the database to check if the transaction was recorded
        PreparedStatement pstmt = connection.prepareStatement("SELECT COUNT(*) FROM UPI_Transactions WHERE SENDER_UPI_ID = ?");
        pstmt.setString(1, "deepak@oksbi");
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        
        // Assert that at least one transaction was recorded
        assertTrue(count > 0);
    }
}
