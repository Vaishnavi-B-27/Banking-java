package com.training.banking.model;

public class CurrentAccount extends Account {
    private final double overdraftLimit;

    public CurrentAccount(
            String accountNumber,
            String holderName,
            double openingBalance,
            double overdraftLimit) {
        super(accountNumber, holderName, openingBalance);

        if (overdraftLimit < 0) {
            throw new IllegalArgumentException("Overdraft limit cannot be negative.");
        }

        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        validatePositiveAmount(amount);

        if (getBalance() - amount < -overdraftLimit) {
            throw new IllegalArgumentException("Withdrawal denied: overdraft limit exceeded.");
        }

        debit(amount);
        System.out.printf("Withdrawn %.2f from current account %s%n", amount, getAccountNumber());
    }

    @Override
    public String getAccountType() {
        return "CurrentAccount";
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }
}
