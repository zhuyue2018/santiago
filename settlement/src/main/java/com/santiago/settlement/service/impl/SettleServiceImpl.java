package com.santiago.settlement.service.impl;

import com.santiago.settlement.entity.domain.Account;
import com.santiago.settlement.entity.domain.AccountHistory;
import com.santiago.settlement.entity.domain.MerchantSettleConfig;
import com.santiago.settlement.entity.domain.SettRecord;
import com.santiago.settlement.entity.enums.SettRecordStatusEnum;
import com.santiago.settlement.mapper.SettRecordMapper;
import com.santiago.settlement.service.AccountService;
import com.santiago.settlement.service.SettleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2019-10-25 12:16
 **/
@Service
public class SettleServiceImpl implements SettleService {
    @Autowired
    SettRecordMapper settRecordMapper;
    @Autowired
    AccountService accountService;
    @Override
    public void settle(MerchantSettleConfig settleConfig, AccountHistory accountHistory) {
        SettRecord settRecord = new SettRecord();
        settRecord.setVersion("1.0.0");
        settRecord.setGmtCreate(new Date());
        settRecord.setGmtModified(new Date());
        settRecord.setAccountNo(accountHistory.getAccountNo());
        settRecord.setMerchantNo(accountHistory.getMerchantNo());
        if ("toBalance".equals(settleConfig.getSettleType())) {
            settRecord.setSettMode("toBalance");
            Account account = accountService.getByAccountNo(accountHistory.getAccountNo());
            account.setUnsettBalance(account.getUnsettBalance().subtract(accountHistory.getAmount()));
            account.setBalance(account.getBalance().add(accountHistory.getAmount()));
            accountService.update(account);
        } else if ("toBankAccount".equals(settleConfig.getSettleType())) {

        } else {

        }
        settRecord.setSettStatus(SettRecordStatusEnum.REMIT_SUCCESS.getCode());
        settRecordMapper.insert(settRecord);
    }
}
