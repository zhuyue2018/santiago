package com.santiago.account.service.impl;

import com.santiago.account.domain.entity.Account;
import com.santiago.account.mapper.AccountMapper;
import com.santiago.account.service.AccountService;
import com.santiago.commons.enums.PublicStatusEnum;
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
    public void createDefaultAccount(String accountNo, String merchantNo) {
        Account account = new Account();
        account.setGmtCreate(new Date());
        account.setGmtModified(new Date());
        account.setVersion("1.0.0");
        account.setRemark("2");
        account.setAccountNo(accountNo);
        account.setBalance(BigDecimal.ZERO);
        account.setFreezeBalance(BigDecimal.ZERO);
        account.setSecurityMoney(BigDecimal.ZERO);
        account.setStatus(PublicStatusEnum.ACTIVE.getCode());
        account.setTotalExpend(BigDecimal.ZERO);
        account.setTotalIncome(BigDecimal.ZERO);
        account.setTodayIncome(BigDecimal.ZERO);
        account.setTodayExpend(BigDecimal.ZERO);
        account.setAccountType("0");
        account.setUnsettBalance(BigDecimal.ZERO);
        account.setMerchantNo(merchantNo);
        account.setDeleteFlag("0");
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
