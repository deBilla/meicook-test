package com.dimuthu.bankAccount.entitities;

import com.dimuthu.bankAccount.exceptions.AgeException;
import com.dimuthu.bankAccount.exceptions.NotSupportedCurrencyException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountTest {

    @Test
    void When_Age_Below_18() {
        Throwable throwable =  assertThrows(Throwable.class, () -> {
            User user = new User("Dimuthu", 17);
            Account account = new Account(user, "USD", UUID.randomUUID());
        });

        assertEquals(AgeException.class, throwable.getClass());
    }

    @Test
    void When_Age_Below_18_And_Set_Later() {
        Throwable throwable =  assertThrows(Throwable.class, () -> {
            User user = new User("Dimuthu", 20);
            user.setAge(17);
            Account account = new Account(user, "USD", UUID.randomUUID());
        });

        assertEquals(AgeException.class, throwable.getClass());
    }

    @Test
    void When_Age_Greater_Than_18() {
        User user = new User("Dimuthu", 19);
        Account account = new Account(user, "USD", UUID.randomUUID());

        assertEquals(user, account.getUser());
        assertEquals("USD", account.getAccountCurrency());
        assertEquals(BigDecimal.valueOf(0), account.getBalance());
    }

    @Test
    void When_Currency_Is_Wrong() {
        Throwable throwable =  assertThrows(Throwable.class, () -> {
            User user = new User("Dimuthu", 20);
            Account account = new Account(user, "USDD", UUID.randomUUID());
        });

        assertEquals(NotSupportedCurrencyException.class, throwable.getClass());
    }

    @Test
    void When_Currency_Is_Wrong_And_Set_Later() {
        Throwable throwable =  assertThrows(Throwable.class, () -> {
            User user = new User("Dimuthu", 20);
            Account account = new Account(user, "USD", UUID.randomUUID());
            account.setAccountCurrency("USDD");
        });

        assertEquals(NotSupportedCurrencyException.class, throwable.getClass());
    }
}