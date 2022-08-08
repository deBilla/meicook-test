package com.dimuthu.bankAccount.services.implementation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dimuthu.bankAccount.entitities.Account;
import com.dimuthu.bankAccount.entitities.Transaction;
import com.dimuthu.bankAccount.exceptions.AccountBalanceInsufficientException;
import com.dimuthu.bankAccount.exceptions.AccountDoesNotExistException;
import com.dimuthu.bankAccount.repositories.AccountRepository;
import com.dimuthu.bankAccount.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dimuthu.bankAccount.services.AccountService;

@Service
public class AccountServiceImplementation implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    private static final BigDecimal minBalance = new BigDecimal("200.00");

    @Override
    public Account createAccount(Account account) {
        userRepository.save(account.getUser());
        accountRepository.save(account);
        return account;
    }

    @Override
    public Transaction withDraw(Transaction transaction) {
        Optional<Account> userAccount = accountRepository.findById(transaction.getAccountUuid());

        if (userAccount.isEmpty()) throw new AccountDoesNotExistException("Account doesn't exist");

        if (userAccount.get().getBalance().subtract(transaction.getWithdrawAmount()).compareTo(minBalance) >= 0) {
            userAccount.get().setBalance(userAccount.get().getBalance().subtract(transaction.getWithdrawAmount()));
            accountRepository.save(userAccount.get());
            transaction.setBalance(userAccount.get().getBalance());
        } else {
            throw new AccountBalanceInsufficientException("Withdraw amount should be larger than minimum balance");
        }

        return transaction;
    }

    @Override
    public Transaction deposit(Transaction transaction) {
        Optional<Account> userAccount = accountRepository.findById(transaction.getAccountUuid());

        if (userAccount.isEmpty()) throw new AccountDoesNotExistException("Account doesn't exist");

        userAccount.get().setBalance(userAccount.get().getBalance().add(transaction.getDepositAmount()));
        accountRepository.save(userAccount.get());
        transaction.setBalance(userAccount.get().getBalance());

        return transaction;
    }
}
