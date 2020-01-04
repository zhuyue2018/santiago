package com.santiago.account.controller;

import com.santiago.account.entity.domain.Account;
import com.santiago.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
@Controller
@RequestMapping(value = "/account")
public class AccountWss {
    @Autowired
    AccountService accountService;
    public List<Account> listAll() {
        return accountService.listAll();
    }

    @PostMapping(value = "/create")
    public void create(String accountNo, String merchantNo) {
        accountService.createDefaultAccount(accountNo, merchantNo);
    }
}
