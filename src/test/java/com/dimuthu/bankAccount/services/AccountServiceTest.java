package com.dimuthu.bankAccount.services;

import com.dimuthu.bankAccount.entitities.Account;
import com.dimuthu.bankAccount.entitities.Transaction;
import com.dimuthu.bankAccount.entitities.User;
import com.dimuthu.bankAccount.exceptions.AccountBalanceInsufficientException;
import com.dimuthu.bankAccount.repositories.AccountRepository;
import com.dimuthu.bankAccount.repositories.UserRepository;
import com.dimuthu.bankAccount.services.implementation.AccountServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class AccountServiceTest {
    @InjectMocks
    AccountServiceImplementation accountService;

    @Mock
    AccountRepository accountRepository;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void When_Withdraw_Amount_Larger_than_Balance() {
        Throwable throwable =  assertThrows(Throwable.class, () -> {
            User user = new User("dimuthu", 19);
            Account account = new Account(user, "USD");

            when(userRepository.save(Mockito.any())).thenReturn(user);
            when(accountRepository.save(Mockito.any())).thenReturn(account);
            when(accountRepository.findById(Mockito.any())).thenReturn(Optional.of(account));

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

        when(userRepository.save(Mockito.any())).thenReturn(user);
        when(accountRepository.save(Mockito.any())).thenReturn(account);
        when(accountRepository.findById(Mockito.any())).thenReturn(Optional.of(account));

        Account userAcc = accountService.createAccount(account);
        Transaction transactionDeposit = new Transaction(userAcc.getUuid(), new BigDecimal(0), new BigDecimal(300));
        Transaction transactionWithDraw = new Transaction(userAcc.getUuid(), new BigDecimal(100), new BigDecimal(0));

        accountService.deposit(transactionDeposit);
        accountService.withDraw(transactionWithDraw);

        assertEquals(new BigDecimal(200), userAcc.getBalance());
    }
}