package com.santiago.account.controller;

import com.santiago.account.api.AccountDTO;
import com.santiago.account.entity.domain.Account;
import com.santiago.account.service.AccountService;
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
    public List<AccountDTO> list() {
        List<Account> accounts = accountService.listAll();
        return convert(accounts);
    }

    private List<AccountDTO> convert(List<Account> accounts) {
        return null;
    }

    @GetMapping(value = "/api/accounts/{id}")
    public AccountDTO get(@PathVariable String id) {
        return convert(accountService.getByAccountNo(id));
    }

    private AccountDTO convert(Account account) {
        return null;
    }

    @PostMapping(value = "/api/accounts")
    public UnionResp create(String accountNo, String merchantNo) {
        accountService.createDefaultAccount(accountNo, merchantNo);
        return new UnionResp("200", "账户创建成功");
    }

}
