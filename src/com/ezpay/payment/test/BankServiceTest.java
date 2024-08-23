/**
 * Module Name: BankServiceTest
 *
 * Description: This module tests the functionality of the BankService class, ensuring that bank account
 * transactions are processed correctly based on various conditions like sufficient balance, invalid account 
 * numbers, etc.
 * 
 * Author:
 * Aishveen Kaur
 * 
 * Date: August 22, 2024
 */

package com.ezpay.payment.test;

import com.ezpay.payment.repository.BankTransactionRepository;
import com.ezpay.payment.model.BankTransaction;
import com.ezpay.payment.repository.BankUserRepository;
import com.ezpay.payment.service.BankService;
import com.ezpay.payment.util.DBConnection;
import org.junit.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BankServiceTest {

    private BankService bankService;
    private BankUserRepository bankUserRepository;
    private BankTransactionRepository bankTransactionRepository;
    private Connection connection;

    @Before
    public void setUp() throws SQLException {
        // Setup Oracle Database connection using DBConnection
        connection = DBConnection.getConnection();
        
        connection.setAutoCommit(false);

        bankUserRepository = new BankUserRepository(connection);
        bankTransactionRepository = new BankTransactionRepository(connection);
        bankService = new BankService(bankUserRepository, bankTransactionRepository);

        // Insert initial test data
        insertTestData(connection);
    }

    @After
    public void tearDown() throws SQLException {
        // Roll back the transaction to keep the database state unchanged
        if (connection != null && !connection.isClosed()) {
            connection.rollback();
            connection.close();
        }
    }


    @Test
    // Test case for valid account number verification
    public void testVerifyAccountNumber_Valid() {
        String result = bankService.verifyAccountNumber("12345");
        assertEquals("verified", result);
    }

    @Test
    // Test case for invalid account number verification
    public void testVerifyAccountNumber_Invalid() {
        String result = bankService.verifyAccountNumber("99999");
        assertEquals("Invalid Account Number", result);
    }

    @Test
    // Test case for valid ifsc code verification
    public void testVerifyIfscCode_Valid() {
        String result = bankService.verifyIfscCode("12345", "IFSC001");
        assertEquals("verified", result);
    }

    @Test
    // Test case for invalid ifsc code verification
    public void testVerifyIfscCode_Invalid() {
        String result = bankService.verifyIfscCode("12345", "INVALID_IFSC");
        assertEquals("Invalid IFSC Code", result);
    }

    @Test
    // Test case to check successful transaction
    public void testProcessPayment_Success() {
        String result = bankService.processPayment("12345", "IFSC001", "67890", 500.0, "Test Payment");
        assertEquals("Transaction Successful.", result);

        double senderBalance = bankService.getBalance("12345");
        double receiverBalance = bankService.getBalance("67890");

        assertEquals(4500.0, senderBalance, 0.001);
        assertEquals(3500.0, receiverBalance, 0.001);
    }

    @Test
    // Test case to check insufficient funds case
    public void testProcessPayment_InsufficientFunds() {
        String result = bankService.processPayment("12345", "IFSC001", "67890", 6000.0, "Test Payment");
        assertEquals("Error: Insufficient funds.", result);
    }

    @Test
    // Test case to check the amount in a particular user account
    public void testGetBalance() {
        double balance = bankService.getBalance("12345");
        assertEquals(5000.0, balance, 0.001);
    }

    @Test
    // Test case to check the transaction history
    public void testGetTransactionHistory() {
        bankService.processPayment("12345", "IFSC001", "67890", 500.0, "Test Payment");

        List<BankTransaction> transactions = bankService.getTransactionHistory("12345");
        assertEquals(1, transactions.size());
        assertEquals(500.0, transactions.get(0).getAmount(), 0.001);
    }

    // Helper methods to set up the Oracle database and test data

    private void insertTestData(Connection connection) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("INSERT INTO bank_user (CUST_NAME, ACCOUNT_NUMBER, IFSC_CODE, BALANCE) VALUES " +
                    "('John Doe', '12345', 'IFSC001', 5000.0)");
            stmt.execute("INSERT INTO bank_user (CUST_NAME, ACCOUNT_NUMBER, IFSC_CODE, BALANCE) VALUES " +
                    "('Jane Doe', '67890', 'IFSC002', 3000.0)");
        }
    }
}
