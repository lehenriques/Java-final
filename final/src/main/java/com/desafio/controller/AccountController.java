package com.desafio.controller;

import com.desafio.exception.ResourceNotFoundException;
import com.desafio.model.Account;
import com.desafio.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/contas")
    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    @PostMapping("/conta")
    public Account createAccount(@RequestBody Account account) {
        return accountRepository.save(account);
    }

    @GetMapping("/conta/{id}")
    public Account getAccountById(@PathVariable(value = "id") Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "id", accountId));
    }

    @PutMapping("/conta/{id}")
    public Account updateAccount(@PathVariable(value = "id") Long accountId,
                               @RequestBody Account accountDetails) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "id", accountId));

        account.setUniversityCod(accountDetails.getUniversityCod());
        account.setCourseCod(accountDetails.getCourseCod());
        account.setMovimentacao(accountDetails.getMovimentacao());
        account.setSaldo(accountDetails.getSaldo());
        account.setDateMovimentacao(accountDetails.getDateMovimentacao());

        Account updateAccount = accountRepository.save(account);
        return updateAccount;
    }

    @DeleteMapping("/conta/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable(value = "id") Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "id", accountId));

        accountRepository.delete(account);

        return ResponseEntity.ok().build();
    }

    public Account depositAccount(@PathVariable(value = "id") Long accountId,
                                @RequestBody Account accountDetails) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "id", accountId));

        account.deposit(accountDetails.getSaldo());

        Account updateAccount = accountRepository.save(account);
        return updateAccount;

    }

    public Account withdrawAccount(@PathVariable(value = "id") Long accountId,
                                  @RequestBody Account accountDetails) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "id", accountId));

        account.withdraw(accountDetails.getSaldo());

        Account updateAccount = accountRepository.save(account);
        return updateAccount;

    }
}
