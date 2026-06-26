# Banking Project

A simple Java console-based banking application that demonstrates object-oriented programming concepts such as abstraction, inheritance, interfaces, polymorphism, and encapsulation.

## Features

- Create and manage different account types
- Deposit money into accounts
- Withdraw money with account-specific rules
- Transfer money between accounts
- Apply interest to eligible accounts
- Display account details

## Account Types

### Savings Account
- Maintains a minimum balance
- Supports deposits and withdrawals
- Earns interest based on the current balance

### Current Account
- Supports overdraft withdrawals up to a defined overdraft limit
- Does not earn interest

### Fixed Deposit Account
- Earns interest based on term duration and annual interest rate
- Allows withdrawal with penalty if withdrawn before maturity

## Project Structure

```text
Banking/
├── src/
│   └── com/
│       └── training/
│           └── banking/
│               ├── BankingApp.java
│               ├── interfaces/
│               │   ├── InterestBearing.java
│               │   └── Transferable.java
│               ├── model/
│               │   ├── Account.java
│               │   ├── SavingsAccount.java
│               │   ├── CurrentAccount.java
│               │   └── FixedDepositAccount.java
│               └── service/
│                   └── BankingService.java
└── out/
