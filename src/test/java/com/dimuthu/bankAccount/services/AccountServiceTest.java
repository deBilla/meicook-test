package com.dimuthu.bankAccount.services;

import com.dimuthu.bankAccount.entitities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {
    private final AccountService accountService = new AccountService();

    @Test
    void When_Withdraw_Amount_Larger_than_Balance() {
        User user = new User();
        user.setName("dimuthu");
        user.setAge(17);

        Account userAcc = accountService.createAccount(user, "USD");

        assertFalse(accountService.withDraw(userAcc.getUuid(), new BigDecimal(100)));
    }

    @Test
    void When_Withdraw_Amount_smaller_than_Balance() {
        User user = new User();
        user.setName("dimuthu");
        user.setAge(17);

        Account userAcc = accountService.createAccount(user, "USD");
        accountService.deposit(userAcc.getUuid(), new BigDecimal(300));

        assertTrue(accountService.withDraw(userAcc.getUuid(), new BigDecimal(100)));
    }
}