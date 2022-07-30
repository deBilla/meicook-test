package com.dimuthu.bankAccount.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.dimuthu.bankAccount.entitities.Account;
import com.dimuthu.bankAccount.entitities.User;

public class AccountService {
    private final List<Account> accountList;
    private static final BigDecimal minBalance = new BigDecimal("200.00");

    public AccountService() {
        this.accountList = new ArrayList<>();
    }

    public Account createAccount(User user, String accountCurrency) {
        UUID accountUuid = UUID.randomUUID();
        Account newUserAccount = new Account();
        newUserAccount.setUser(user);
        newUserAccount.setAccountCurrency(accountCurrency);
        newUserAccount.setUuid(accountUuid);
        newUserAccount.setBalance(new BigDecimal(0));

        this.accountList.add(newUserAccount);

        return newUserAccount;
    }

    public boolean withDraw(UUID accountUuid, BigDecimal amount) {
        Account userAccount = accountList.stream().filter(account -> accountUuid.equals(account.getUuid()))
                .findAny()
                .orElse(null);

        if (userAccount == null) return false;

        if (userAccount.getBalance().subtract(amount).compareTo(minBalance) == 0) {
            userAccount.setBalance(userAccount.getBalance().subtract(amount));

            return true;
        }

        return false;
    }

    public void deposit(UUID accountUuid, BigDecimal amount) {
        Account userAccount = accountList.stream().filter(account -> accountUuid.equals(account.getUuid()))
                .findAny()
                .orElse(null);

        if (userAccount == null) return;

        userAccount.setBalance(userAccount.getBalance().add(amount));
    }
}
