package com.santiago.account.controller;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;
import com.santiago.account.service.AccountService;
import com.santiago.api.dto.Account;
import com.santiago.commons.dto.resp.UnionResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 账户处理接口
 */
@RestController
public class AccountWss {
    @Autowired
    AccountService accountService;
    @GetMapping(value = "/api/accounts")
    public List<Account> list() {
        return accountService.listAll();
    }

    @GetMapping(value = "/api/accounts/{id}")
    public Account get(@PathVariable String id) {
        return accountService.getByAccountNo(id);
    }

    @PostMapping(value = "/api/accounts")
    public UnionResp create(String accountNo, String merchantNo) {
        accountService.createDefaultAccount(accountNo, merchantNo);
        return new UnionResp("200", "账户创建成功");
    }

}
