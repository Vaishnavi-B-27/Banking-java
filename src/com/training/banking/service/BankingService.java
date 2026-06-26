package com.training.banking.service;

import com.training.banking.interfaces.InterestBearing;
import com.training.banking.model.Account;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class BankingService {
    private final Map<String, Account> accounts = new LinkedHashMap<>();

    public void addAccount(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account is required.");
        }
        if (accounts.containsKey(account.getAccountNumber())) {
            throw new IllegalArgumentException("Account number already exists.");
        }

        accounts.put(account.getAccountNumber(), account);
    }

    public Account findAccount(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found: " + accountNumber);
        }
        return account;
    }

    public void deposit(String accountNumber, double amount) {
        findAccount(accountNumber).deposit(amount);
    }

    public void withdraw(String accountNumber, double amount) {
        findAccount(accountNumber).withdraw(amount);
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        Account fromAccount = findAccount(fromAccountNumber);
        Account toAccount = findAccount(toAccountNumber);
        fromAccount.transferTo(toAccount, amount);
    }

    public void applyInterestToAllEligibleAccounts() {
        for (Account account : accounts.values()) {
            if (account instanceof InterestBearing interestBearing) {
                interestBearing.applyInterest();
            }
        }
    }

    public void displayAllAccounts() {
        for (Account account : accounts.values()) {
            account.display();
        }
    }

    public Collection<Account> getAccounts() {
        return accounts.values();
    }
}
