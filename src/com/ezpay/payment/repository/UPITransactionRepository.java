package com.ezpay.payment.repository;
import com.ezpay.payment.model.UPITransaction;
import java.util.ArrayList;
import java.util.List;

public class UPITransactionRepository {
    private List<UPITransaction> upitransactions = new ArrayList<>();

    public void saveTransaction(UPITransaction upitransaction) {
        upitransactions.add(upitransaction);
    }

    public List<UPITransaction> getAllTransactions() {
        return upitransactions;
    }
}