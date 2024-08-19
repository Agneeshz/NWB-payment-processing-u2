/**
 * Module Name: UPIRepository
 * 
 * Description:This module serves as the repository for storing and managing UPI details.
 * It contains a list of predefined bank accounts and provides functionality to update account details such as
 * balance after a transaction.
 * 
 * Author:
 * Adithya Mode
 * 
 * Date: August 10,2024
 */

package com.ezpay.payment.repository;

import com.ezpay.payment.model.UPI;
import java.util.HashMap;
import java.util.Map;

public class UPIRepository {
    private Map<String, UPI> users = new HashMap<>();

    public UPIRepository() {
        // Initializing with some sample users
        users.put("adithya@oksbi", new UPI("Adithya Mode", "adithya@oksbi", 5000, 8105206202L, "adithya@gmail.com"));
        users.put("agneesh@oksbi", new UPI("Agneesh Dasgupta", "agneesh@oksbi", 8000, 9563214852L, "agneesh@gmail.com"));
        users.put("deepak@oksbi", new UPI("Deepak Reddy", "deepak@oksbi", 10000, 8105206202L, "deepak@gmail.com"));
        users.put("aishveen@oksbi", new UPI("Aishveen Kaur", "aishveen@oksbi", 12000, 8105206202L, "aishveen@gmail.com"));
        users.put("hasini@oksbi", new UPI("Hasini", "hasini@oksbi", 15000, 8105206202L, "hasini@gmail.com"));
    }

    public UPI findUserByUpiId(String upiId) {
        return users.get(upiId);
    }
}
