package com.training.banking.interfaces;

import com.training.banking.model.Account;

public interface Transferable {
    void transferTo(Account targetAccount, double amount);
}
