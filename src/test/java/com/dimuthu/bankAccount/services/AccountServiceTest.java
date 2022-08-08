package com.dimuthu.bankAccount.services;

import com.dimuthu.bankAccount.entitities.Account;
import com.dimuthu.bankAccount.entitities.User;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {
    private final AccountService accountService = new AccountService();

    @Test
    void When_Withdraw_Amount_Larger_than_Balance() throws Exception {
        User user = new User("dimuthu", 19);

        Account userAcc = accountService.createAccount(user, "USD");

        assertFalse(accountService.withDraw(userAcc.getUuid(), new BigDecimal(100)));
        assertEquals(new BigDecimal(0), userAcc.getBalance());
    }

    @Test
    void When_Withdraw_Amount_smaller_than_Balance() throws Exception {
        User user = new User("dimuthu", 29);

        Account userAcc = accountService.createAccount(user, "USD");
        accountService.deposit(userAcc.getUuid(), new BigDecimal(300));

        assertTrue(accountService.withDraw(userAcc.getUuid(), new BigDecimal(100)));
        assertEquals(new BigDecimal(200), userAcc.getBalance());
    }
}