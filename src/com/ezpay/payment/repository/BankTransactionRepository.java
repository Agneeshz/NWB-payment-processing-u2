package com.ezpay.payment.repository;

import com.ezpay.payment.model.BankTransaction;
import java.util.ArrayList;
import java.util.List;

public class BankTransactionRepository {

    private List<BankTransaction> bankTransactions = new ArrayList<>();

    public void saveTransaction(BankTransaction transaction) {
        bankTransactions.add(transaction);
    }

    public List<BankTransaction> getAllTransactions() {
        return bankTransactions;
    }

}