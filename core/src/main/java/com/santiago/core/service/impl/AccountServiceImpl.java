package com.santiago.core.service.impl;

import com.santiago.commons.enums.PublicStatusEnum;
import com.santiago.core.entity.domain.Account;
import com.santiago.core.mapper.AccountMapper;
import com.santiago.core.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountMapper accountMapper;
    @Override
    public void createDaefaultAccount(String accountNo, String merchantNo) {
        Account account = new Account();
        account.setGmtCreate(new Date());
        account.setGmtModified(new Date());
        account.setVersion("1.0.0");
        account.setRemark("2");
        account.setAccountNo(accountNo);
        account.setBalance(new BigDecimal("0"));
        account.setFreezeBalance(new BigDecimal("0"));
        account.setSecurityMoney(new BigDecimal("0"));
        account.setStatus(PublicStatusEnum.ACTIVE.getCode());
        account.setTotalExpend(new BigDecimal("0"));
        account.setTotalIncome(new BigDecimal("0"));
        account.setTodayIncome(new BigDecimal("0"));
        account.setTodayExpend(new BigDecimal("0"));
        account.setAccountType("0");
        account.setMerchantNo(merchantNo);
        account.setDelete("0");
        accountMapper.insert(account);
    }
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

    @Override
    public List<Account> listAll() {
        Account account = new Account();
        return list(account);
    }
}
