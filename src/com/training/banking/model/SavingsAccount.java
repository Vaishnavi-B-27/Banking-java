package com.training.banking.model;

import com.training.banking.interfaces.InterestBearing;

public class SavingsAccount extends Account implements InterestBearing {
    private final double interestRate;
    private final double minimumBalance;

    public SavingsAccount(
            String accountNumber,
            String holderName,
            double openingBalance,
            double interestRate,
            double minimumBalance) {
        super(accountNumber, holderName, openingBalance);

        if (interestRate < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative.");
        }
        if (minimumBalance < 0) {
            throw new IllegalArgumentException("Minimum balance cannot be negative.");
        }
        if (openingBalance < minimumBalance) {
            throw new IllegalArgumentException("Opening balance must satisfy the minimum balance.");
        }

        this.interestRate = interestRate;
        this.minimumBalance = minimumBalance;
    }

    @Override
    public void withdraw(double amount) {
        validatePositiveAmount(amount);

        if (getBalance() - amount < minimumBalance) {
            throw new IllegalArgumentException("Withdrawal denied: minimum balance must be maintained.");
        }

        debit(amount);
        System.out.printf("Withdrawn %.2f from savings account %s%n", amount, getAccountNumber());
    }

    @Override
    public double calculateInterest() {
        return getBalance() * interestRate;
    }

    @Override
    public void applyInterest() {
        double interest = calculateInterest();
        credit(interest);
        System.out.printf("Applied savings interest %.2f to %s%n", interest, getAccountNumber());
    }

    @Override
    public String getAccountType() {
        return "SavingsAccount";
    }

    public double getInterestRate() {
        return interestRate;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }
}
