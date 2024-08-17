package Controller;

import java.util.Scanner;

import Service.BankService;

public class BankTransferPaymentController {

    public void bankDetails()
    {
        // Setup repositories
        Repo.BankUserRepository bankuserRepository = new Repo.BankUserRepository();
        Repo.BankTransactionRepository banktransactionRepository = new Repo.BankTransactionRepository();
        
        // Setup bank service
        Service.BankService bankService = new BankService(bankuserRepository, banktransactionRepository);
        
        // User inputs
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your Account Number:");
        String senderAccountNumber = scanner.nextLine();

        //verifying sender account number
        String accountNumberverification = bankService.verifyAccountNumber(senderAccountNumber);

        if(accountNumberverification.equalsIgnoreCase("verified"))
        {
            System.out.println("Enter IFSC Code:");
            String ifscCode = scanner.nextLine();

            //verifying ifsc code
            String ifscCodeverification = bankService.verifyIfscCode(senderAccountNumber, ifscCode);

            if(ifscCodeverification.equalsIgnoreCase("verified"))
            {
                System.out.println("Enter recipent account number:");
                String receiverAccountNumber = scanner.nextLine();

                //verify receiver account number
                String receiverAccountNumberverification = bankService.verifyAccountNumber(receiverAccountNumber);

                if(receiverAccountNumberverification.equalsIgnoreCase("verified"))
                {
                    System.out.println("Enter amount to send:");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // consume newline
        
                    System.out.println("Enter note for the transaction (optional):");
                    String note = scanner.nextLine();
        
                    // Confirm transaction details
                    System.out.println("Please confirm your details");
                    System.out.println("Sender Account Number : " + senderAccountNumber);
                    System.out.println("Sender IFSC Code : " + ifscCode);
                    System.out.println("Receiver Account Number:" + receiverAccountNumber);
                    System.out.println("Amount : " + amount);
                    System.out.println("Enter YES to initiate the transaction");
                    String confirm = scanner.nextLine();
        
                    if (confirm.equalsIgnoreCase("yes")) {
                        String result = bankService.processPayment(senderAccountNumber, ifscCode, receiverAccountNumber, amount, note);
                        System.out.println(result);
                    } else {
                        System.out.println("Transaction Aborted.");
                    }
                }

                else
                {
                    System.out.println(receiverAccountNumberverification);
                    System.out.println("Transaction cancelled !!");
                }

            }

            else
            {
                System.out.println(ifscCodeverification);
                System.out.println("Transaction cancelled !!");
            }
        }

        else
        {
            System.out.println(accountNumberverification);
            System.out.println("Transaction cancelled !!");
        }

        scanner.close();
    }
}
