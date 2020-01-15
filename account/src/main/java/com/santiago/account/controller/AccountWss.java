package com.santiago.account.controller;

import com.santiago.account.entity.domain.Account;
import com.santiago.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class AccountWss {
    @Autowired
    AccountService accountService;
    @GetMapping(value = "/api/accounts")
    public List<Account> list() {
        return accountService.listAll();
    }

    @GetMapping(value = "/api/accounts/{id}")
    public Account get() {
        return accountService.getByAccountNo("");
    }

    @PostMapping(value = "/api/accounts")
    public void create(String accountNo, String merchantNo) {
        accountService.createDefaultAccount(accountNo, merchantNo);

    }

//    @PostMapping(value = "/asyncAccount")
//    public void asyncAccount(TransactionDTO transactionDTO) {
//        accountService.insertTransaction();
//        accountService.send2Kafka();
//    }
//
//    @PostMapping(value = "/account")
//    public void account() {
//        accountService.insertTransaction();
//        accountService.account();
//    }

}
