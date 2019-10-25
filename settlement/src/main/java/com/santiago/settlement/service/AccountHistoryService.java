package com.santiago.settlement.service;

import com.santiago.settlement.entity.domain.AccountHistory;

import java.util.List;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2019-10-25 13:15
 **/
public interface AccountHistoryService {
    AccountHistory getByMerchantNoAndRecDate(String merchantNo, String date);
    List<AccountHistory> list(AccountHistory accountHistory);
    AccountHistory get(AccountHistory accountHistory);
    void update(AccountHistory accountHistory);
}
