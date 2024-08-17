package Service;

import Model.BankTransaction;
import Model.BankUser;
import Repo.BankTransactionRepository;
import Repo.BankUserRepository;


import java.util.Date;

public class BankService {

    private BankUserRepository bankuserRepository;
    private BankTransactionRepository banktransactionRepository;

    public BankService(BankUserRepository bankuserRepository, BankTransactionRepository banktransactionRepository) {
        this.bankuserRepository = bankuserRepository;
        this.banktransactionRepository = banktransactionRepository;
    }

    //account number verification
    public String verifyAccountNumber(String accountNumber)
    {
        BankUser sender = bankuserRepository.findUserByAccountNumber(accountNumber);

        if(sender == null)
        {
            return "Invalid Account Number";
        }

        return "verified";
    }

    //ifsc code verification
    public String verifyIfscCode(String accountNumber, String ifscCode)
    {
        BankUser sender = bankuserRepository.findUserByAccountNumber(accountNumber);

        if(sender.getIfscCode().equalsIgnoreCase(ifscCode))
        {
            return "Verified";
        }

        return "Invalid IFSC Code";
    }

    public String processPayment(String senderAccountNumber, String ifscCode, String receiverAccountNumber, double amount, String note) {
        BankUser sender = bankuserRepository.findUserByAccountNumber(senderAccountNumber);
        BankUser receiver = bankuserRepository.findUserByAccountNumber(receiverAccountNumber);

        // Check if the account has sufficient balance
        if (sender.getBalance() < amount) {
            return "Error: Insufficient funds.";
        }

        // Deduct amount from sender and add to receiver
        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        // Record the transaction
        BankTransaction bankTransaction = new BankTransaction(senderAccountNumber, ifscCode, receiverAccountNumber, amount, new Date(), note, "Success");
        banktransactionRepository.saveTransaction(bankTransaction);

        return "Payment of " + amount + " from " + sender.getCustName() + " ACCOUNT NUMBER :" + senderAccountNumber + " To " + receiver.getCustName() + " ACCOUNT NUMBER : " + receiverAccountNumber + " is done." + System.lineSeparator() + "Transaction Successful !!";
    }

}
