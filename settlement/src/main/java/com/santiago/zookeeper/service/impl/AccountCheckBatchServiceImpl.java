package com.santiago.zookeeper.service.impl;

import com.santiago.zookeeper.entity.domain.AccountCheckBatch;
import com.santiago.zookeeper.mapper.AccountCheckBatchMapper;
import com.santiago.zookeeper.service.AccountCheckBatchService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2019-10-30 13:43
 **/
@Service
public class AccountCheckBatchServiceImpl implements AccountCheckBatchService {
    @Autowired
    AccountCheckBatchMapper accountCheckBatchMapper;
    @Override
    public void insert(AccountCheckBatch accountCheckBatch) {
        accountCheckBatchMapper.insert(accountCheckBatch);
    }

    @Override
    public AccountCheckBatch getByBillDate(DateTime date) {
        AccountCheckBatch accountCheckBatch = new AccountCheckBatch();
        accountCheckBatch.setBillDate(date.toDate());
        return get(accountCheckBatch);
    }

    @Override
    public AccountCheckBatch get(AccountCheckBatch accountCheckBatch) {
        return accountCheckBatchMapper.selectOne(accountCheckBatch);
    }
}
