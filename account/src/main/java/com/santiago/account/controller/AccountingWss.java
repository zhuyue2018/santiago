package com.santiago.account.controller;

import com.santiago.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountingWss {
    @Autowired
    AccountService accountService;

//    @PostMapping(value = "/asyncAccounting")
//    public void asyncAccounting(TransactionDTO transactionDTO) {
//        accountService.insertTransaction();
//        accountService.send2Kafka();
//    }
//
//    @PostMapping(value = "/accounting")
//    public void accounting() {
//        accountService.insertTransaction();
//        accountService.account();
//    }

}