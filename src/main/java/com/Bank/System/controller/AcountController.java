package com.Bank.System.controller;

import com.Bank.System.model.Account;
import com.Bank.System.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/account")
public class AcountController {

    private AccountService accountService;

    @Autowired
    public AcountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public void createOrUpdateAccount(@RequestBody Account account) {
        accountService.createOrUpdateAccount(account);
    }

    @GetMapping("/{accountId}")
    public Account getAccount(@PathVariable("accountId") Integer accountId ){
        return accountService.getAccount(accountId);
    }

    @DeleteMapping("/{accountId}")
    public void deleteAccount(@PathVariable("accountId") Integer accountId ){
        accountService.deleteAccount(accountId);
    }


}
