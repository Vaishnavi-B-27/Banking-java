package com.training.banking.model;

import com.training.banking.interfaces.Transferable;

public abstract class Account implements Transferable {
    private final String accountNumber;
    private final String holderName;
    private double balance;

    protected Account(String accountNumber, String holderName, double openingBalance) {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number is required.");
        }
        if (holderName == null || holderName.isBlank()) {
            throw new IllegalArgumentException("Holder name is required.");
        }
        if (openingBalance < 0) {
            throw new IllegalArgumentException("Opening balance cannot be negative.");
        }

        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = openingBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        validatePositiveAmount(amount);
        balance += amount;
        System.out.printf("Deposited %.2f into %s%n", amount, accountNumber);
    }

    public abstract void withdraw(double amount);

    public abstract String getAccountType();

    public void display() {
        System.out.printf(
                "%s [number=%s, holder=%s, balance=%.2f]%n",
                getAccountType(),
                accountNumber,
                holderName,
                balance);
    }

    @Override
    public void transferTo(Account targetAccount, double amount) {
        if (targetAccount == null) {
            throw new IllegalArgumentException("Target account is required.");
        }

        withdraw(amount);
        targetAccount.deposit(amount);
        System.out.printf(
                "Transferred %.2f from %s to %s%n",
                amount,
                accountNumber,
                targetAccount.getAccountNumber());
    }

    protected void debit(double amount) {
        validatePositiveAmount(amount);
        balance -= amount;
    }

    protected void credit(double amount) {
        validatePositiveAmount(amount);
        balance += amount;
    }

    protected void validatePositiveAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
    }
}
