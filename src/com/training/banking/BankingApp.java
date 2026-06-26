package com.training.banking;

import com.training.banking.model.CurrentAccount;
import com.training.banking.model.FixedDepositAccount;
import com.training.banking.model.SavingsAccount;
import com.training.banking.service.BankingService;

public class BankingApp {
    public static void main(String[] args) {
        BankingService bankingService = new BankingService();

        bankingService.addAccount(new SavingsAccount("SAV1001", "Asha Rao", 10000, 0.04, 1000));
        bankingService.addAccount(new CurrentAccount("CUR2001", "Rohan Mehta", 5000, 10000));
        bankingService.addAccount(new FixedDepositAccount("FD3001", "Neha Singh", 50000, 24, 12, 0.065, 0.02));

        System.out.println("Initial account details");
        bankingService.displayAllAccounts();

        System.out.println();
        System.out.println("Performing transactions");
        bankingService.deposit("SAV1001", 2500);
        bankingService.withdraw("CUR2001", 12000);
        bankingService.transfer("SAV1001", "CUR2001", 3000);
        bankingService.withdraw("FD3001", 10000);

        System.out.println();
        System.out.println("Applying interest");
        bankingService.applyInterestToAllEligibleAccounts();

        System.out.println();
        System.out.println("Final account details");
        bankingService.displayAllAccounts();
    }
}
