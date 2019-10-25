package com.santiago.settlement.service.impl;

import com.santiago.settlement.entity.domain.AccountHistory;
import com.santiago.settlement.mapper.AccountHistoryMapper;
import com.santiago.settlement.service.AccountHistoryService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2019-10-25 13:19
 **/
@Service
public class AccountHistoryServiceImpl implements AccountHistoryService {
    @Autowired
    AccountHistoryMapper accountHistoryMapper;
    @Override
    public AccountHistory getByMerchantNoAndRecDate(String merchantNo, String date) {
        AccountHistory accountHistory = new AccountHistory();
        accountHistory.setMerchantNo(merchantNo);
        accountHistory.setRecDate(date);
        return get(accountHistory);
    }

    @Override
    public List<AccountHistory> list(AccountHistory accountHistory) {
        return accountHistoryMapper.select(accountHistory);
    }

    @Override
    public AccountHistory get(AccountHistory accountHistory) {
        return accountHistoryMapper.selectOne(accountHistory);
    }

    @Override
    public void update(AccountHistory accountHistory) {
        accountHistoryMapper.updateByPrimaryKey(accountHistory);
    }


}
