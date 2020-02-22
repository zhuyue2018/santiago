package com.santiago.account.service;

import com.santiago.account.entity.domain.Account;
import com.santiago.account.entity.domain.TransactionDTO;

import java.util.List;

public interface AccountService {
    Account createDefaultAccount(String accountNo, String merchantNo);
    Account getByAccountNo(String accountNo);
    void update(Account account);
    Account get(Account account);
    List<Account> list(Account account);

    List<Account> listAll();

    void insertTransaction(TransactionDTO transactionDTO);

    void accounting(TransactionDTO transactionDTO);

    void asyncAccounting(TransactionDTO transactionDTO);

}
