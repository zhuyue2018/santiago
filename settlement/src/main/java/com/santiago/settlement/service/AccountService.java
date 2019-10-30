package com.santiago.settlement.service;

import com.santiago.core.entity.domain.Account;

import java.util.List;

public interface AccountService {
    Account getByAccountNo(String accountNo);
    void update(Account account);
    Account get(Account account);
    List<Account> list(Account account);

    List<Account> listAll();
}
