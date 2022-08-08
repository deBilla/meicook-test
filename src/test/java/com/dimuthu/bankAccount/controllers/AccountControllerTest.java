package com.dimuthu.bankAccount.controllers;

import com.dimuthu.bankAccount.entitities.Account;
import com.dimuthu.bankAccount.entitities.Transaction;
import com.dimuthu.bankAccount.entitities.User;
import com.dimuthu.bankAccount.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AccountControllerTest {
    @InjectMocks
    AccountController accountController;

    @Mock
    AccountService accountService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void When_all_details_correct_create() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User user = new User("dimuthu", 19, "931881000V");
        Account account = new Account(user, "USD");

        when(accountService.createAccount(Mockito.any())).thenReturn(account);

        Account reponseAcc = accountController.createAccount(account);

        assertEquals(account, reponseAcc);
    }

    @Test
    void When_all_details_correct_deposit() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User user = new User("dimuthu", 19, "931881000V");
        Account account = new Account(user, "USD");
        Transaction transaction = new Transaction(account.getUuid(), BigDecimal.valueOf(0), BigDecimal.valueOf(100));

        when(accountService.deposit(Mockito.any())).thenReturn(transaction);

        Transaction reponseTransaction = accountController.deposit(transaction);

        assertEquals(transaction, reponseTransaction);
    }

    @Test
    void When_all_details_correct_withdraw() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User user = new User("dimuthu", 19, "931881000V");
        Account account = new Account(user, "USD");
        Transaction transaction = new Transaction(account.getUuid(), BigDecimal.valueOf(0), BigDecimal.valueOf(100));

        when(accountService.withDraw(Mockito.any())).thenReturn(transaction);

        Transaction reponseTransaction = accountController.deposit(transaction);

        assertEquals(transaction, reponseTransaction);
    }
}