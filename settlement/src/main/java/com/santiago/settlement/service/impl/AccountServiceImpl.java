package com.santiago.settlement.service.impl;

import com.santiago.settlement.entity.domain.Account;
import com.santiago.settlement.mapper.AccountMapper;
import com.santiago.settlement.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2019-10-25 13:26
 **/
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountMapper accountMapper;
    @Override
    public Account getByAccountNo(String accountNo) {
        Account account = new Account();
        account.setAccountNo(accountNo);
        return get(account);
    }

    @Override
    public void update(Account account) {

    }

    @Override
    public Account get(Account account) {
        return accountMapper.selectOne(account);
    }

    @Override
    public List<Account> list(Account account) {
        return accountMapper.select(account);
    }
}
