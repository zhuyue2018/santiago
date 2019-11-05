package com.santiago.core.wss;

import com.santiago.core.entity.domain.Account;
import com.santiago.core.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
public class AccountWss {
    @Autowired
    AccountService accountService;
    public List<Account> listAll() {
        return accountService.listAll();
    }
}
