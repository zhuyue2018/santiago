package com.santiago.settlement.service.impl;

import com.santiago.settlement.entity.domain.AccountCheckBatch;
import com.santiago.settlement.mapper.AccountCheckBatchMapper;
import com.santiago.settlement.service.AccountCheckBatchService;
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
}
