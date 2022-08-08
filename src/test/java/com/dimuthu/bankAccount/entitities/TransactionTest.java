package com.dimuthu.bankAccount.entitities;

import com.dimuthu.bankAccount.exceptions.AmountCannotBeNegativeException;
import com.dimuthu.bankAccount.exceptions.NotSupportedCurrencyException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void When_Amount_is_Negative() {
        Throwable throwable =  assertThrows(Throwable.class, () -> {
            User user = new User("Dimuthu", 20, "931881000V");
            Account account = new Account(user, "USD");
            Transaction transaction = new Transaction(account.getUuid(), BigDecimal.valueOf(-100), BigDecimal.valueOf(0));
        });

        assertEquals(AmountCannotBeNegativeException.class, throwable.getClass());
    }

    @Test
    void When_Amount_is_positive() {
        User user = new User("Dimuthu", 20, "931881000V");
        Account account = new Account(user, "USD");
        Transaction transaction = new Transaction(account.getUuid(), BigDecimal.valueOf(0), BigDecimal.valueOf(100));

        assertEquals(BigDecimal.valueOf(100), transaction.getDepositAmount());
    }
}