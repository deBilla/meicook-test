package com.dimuthu.bankAccount.controllers;
import com.dimuthu.bankAccount.entitities.Transaction;
import com.dimuthu.bankAccount.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.dimuthu.bankAccount.entitities.Account;

@RestController
@RequestMapping("/account-api/v1")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping(value = "create-account", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Account createAccount(@RequestBody Account account) {
        accountService.createAccount(account);
        return account;
    }

    @PostMapping(value = "deposit", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Transaction deposit(@RequestBody Transaction transaction) {
        accountService.deposit(transaction);
        return transaction;
    }

    @PostMapping(value = "withdraw", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Transaction withdraw(@RequestBody Transaction transaction) {
        accountService.withDraw(transaction);
        return transaction;
    }
}
