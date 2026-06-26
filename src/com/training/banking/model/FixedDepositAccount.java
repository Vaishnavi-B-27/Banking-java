package com.training.banking.model;

import com.training.banking.interfaces.InterestBearing;

public class FixedDepositAccount extends Account implements InterestBearing {
    private final int termMonths;
    private final int completedMonths;
    private final double annualInterestRate;
    private final double earlyWithdrawalPenaltyRate;

    public FixedDepositAccount(
            String accountNumber,
            String holderName,
            double openingBalance,
            int termMonths,
            int completedMonths,
            double annualInterestRate,
            double earlyWithdrawalPenaltyRate) {
        super(accountNumber, holderName, openingBalance);

        if (termMonths <= 0) {
            throw new IllegalArgumentException("Term must be greater than zero months.");
        }
        if (completedMonths < 0 || completedMonths > termMonths) {
            throw new IllegalArgumentException("Completed months must be between zero and the term.");
        }
        if (annualInterestRate < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative.");
        }
        if (earlyWithdrawalPenaltyRate < 0) {
            throw new IllegalArgumentException("Penalty rate cannot be negative.");
        }

        this.termMonths = termMonths;
        this.completedMonths = completedMonths;
        this.annualInterestRate = annualInterestRate;
        this.earlyWithdrawalPenaltyRate = earlyWithdrawalPenaltyRate;
    }

    @Override
    public void withdraw(double amount) {
        validatePositiveAmount(amount);

        double penalty = isMatured() ? 0 : amount * earlyWithdrawalPenaltyRate;
        double totalDebit = amount + penalty;

        if (getBalance() < totalDebit) {
            throw new IllegalArgumentException("Withdrawal denied: insufficient balance for amount and penalty.");
        }

        debit(totalDebit);
        System.out.printf(
                "Withdrawn %.2f from fixed deposit account %s. Penalty: %.2f%n",
                amount,
                getAccountNumber(),
                penalty);
    }

    @Override
    public double calculateInterest() {
        double years = termMonths / 12.0;
        return getBalance() * annualInterestRate * years;
    }

    @Override
    public void applyInterest() {
        double interest = calculateInterest();
        credit(interest);
        System.out.printf("Applied fixed deposit interest %.2f to %s%n", interest, getAccountNumber());
    }

    @Override
    public String getAccountType() {
        return "FixedDepositAccount";
    }

    public boolean isMatured() {
        return completedMonths >= termMonths;
    }

    public int getTermMonths() {
        return termMonths;
    }

    public int getCompletedMonths() {
        return completedMonths;
    }
}
