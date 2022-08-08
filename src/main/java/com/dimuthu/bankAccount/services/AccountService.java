package com.dimuthu.bankAccount.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.dimuthu.bankAccount.entitities.Account;
import com.dimuthu.bankAccount.entitities.Transaction;
import com.dimuthu.bankAccount.entitities.User;
import com.dimuthu.bankAccount.exceptions.AccountBalanceInsufficientException;
import com.dimuthu.bankAccount.exceptions.AccountDoesNotExistException;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final List<Account> accountList;
    private static final BigDecimal minBalance = new BigDecimal("200.00");

    public AccountService() {
        super();
        this.accountList = new ArrayList<>();
    }

    public Account createAccount(Account account) {
        this.accountList.add(account);
        return account;
    }

    public Transaction withDraw(Transaction transaction) {
        Account userAccount = accountList.stream().filter(account -> transaction.getAccountUuid().equals(account.getUuid()))
                .findAny()
                .orElse(null);

        if (userAccount == null) throw new AccountDoesNotExistException("Account doesn't exist");

        if (userAccount.getBalance().subtract(transaction.getWithdrawAmount()).compareTo(minBalance) >= 0) {
            userAccount.setBalance(userAccount.getBalance().subtract(transaction.getWithdrawAmount()));
            transaction.setBalance(userAccount.getBalance());
        } else {
            throw new AccountBalanceInsufficientException("Withdraw amount should be larger than minimum balance");
        }

        return transaction;
    }

    public Transaction deposit(Transaction transaction) {
        Account userAccount = accountList.stream().filter(account -> transaction.getAccountUuid().equals(account.getUuid()))
                .findAny()
                .orElse(null);

        if (userAccount == null) throw new AccountDoesNotExistException("Account doesn't exist");

        userAccount.setBalance(userAccount.getBalance().add(transaction.getDepositAmount()));
        transaction.setBalance(userAccount.getBalance());

        return transaction;
    }
}
