package com.santiago.core.service;

import com.santiago.core.entity.domain.Account;

import java.util.List;

public interface AccountService {
    void createDaefaultAccount(String accountNo, String merchantNo);
    Account getByAccountNo(String accountNo);
    void update(Account account);
    Account get(Account account);
    List<Account> list(Account account);

    List<Account> listAll();
}
