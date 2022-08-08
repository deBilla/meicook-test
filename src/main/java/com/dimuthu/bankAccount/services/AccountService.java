package com.dimuthu.bankAccount.services;
import com.dimuthu.bankAccount.entitities.Account;
import com.dimuthu.bankAccount.entitities.Transaction;

public interface AccountService {
    Account createAccount(Account account);
    Transaction withDraw(Transaction transaction);
    Transaction deposit(Transaction deposit);
}
