package com.dimuthu.bankAccount.services;

import com.dimuthu.bankAccount.entitities.Account;
import com.dimuthu.bankAccount.entitities.Transaction;
import com.dimuthu.bankAccount.entitities.User;
import com.dimuthu.bankAccount.exceptions.AccountBalanceInsufficientException;
import com.dimuthu.bankAccount.exceptions.AgeException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {
    private final AccountService accountService = new AccountService();

    @Test
    void When_Withdraw_Amount_Larger_than_Balance() {
        Throwable throwable =  assertThrows(Throwable.class, () -> {
            User user = new User("dimuthu", 19);
            Account account = new Account(user, "USD");

            Account userAcc = accountService.createAccount(account);
            Transaction transaction = new Transaction(userAcc.getUuid(), new BigDecimal(100), new BigDecimal(0));

            accountService.withDraw(transaction);
        });

        assertEquals(AccountBalanceInsufficientException.class, throwable.getClass());
    }

    @Test
    void When_Withdraw_Amount_smaller_than_Balance() {
        User user = new User("dimuthu", 29);
        Account account = new Account(user, "USD");

        Account userAcc = accountService.createAccount(account);
        Transaction transactionDeposit = new Transaction(userAcc.getUuid(), new BigDecimal(0), new BigDecimal(300));
        Transaction transactionWithDraw = new Transaction(userAcc.getUuid(), new BigDecimal(100), new BigDecimal(0));

        accountService.deposit(transactionDeposit);
        accountService.withDraw(transactionWithDraw);

        assertEquals(new BigDecimal(200), userAcc.getBalance());
    }
}