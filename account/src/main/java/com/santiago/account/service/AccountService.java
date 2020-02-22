package com.santiago.account.service;

import com.santiago.account.domain.entity.TransactionDTO;
import com.santiago.account.entity.domain.Account;

import java.util.List;

public interface AccountService {
    void createDefaultAccount(String accountNo, String merchantNo);
    Account getByAccountNo(String accountNo);
    void update(Account account);
    Account get(Account account);
    List<Account> list(Account account);

    List<Account> listAll();

    void insertTransaction(TransactionDTO transactionDTO);

    void accounting(TransactionDTO transactionDTO);

    void asyncAccounting();

}
