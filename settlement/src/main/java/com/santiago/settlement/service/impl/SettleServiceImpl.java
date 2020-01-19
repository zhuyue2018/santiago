package com.santiago.settlement.service.impl;

import com.santiago.commons.enums.VersionEnum;
import com.santiago.settlement.entity.domain.AccountHistory;
import com.santiago.settlement.entity.domain.MerchantSettleConfig;
import com.santiago.settlement.entity.domain.SettRecord;
import com.santiago.settlement.entity.enums.SettRecordStatusEnum;
import com.santiago.settlement.mapper.SettRecordMapper;
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
    @Override
    public void settle(MerchantSettleConfig settleConfig, AccountHistory accountHistory) {
        SettRecord settRecord = new SettRecord();
        settRecord.setVersion(VersionEnum.INIT.getCode());
        settRecord.setGmtCreate(new Date());
        settRecord.setGmtModified(new Date());
        settRecord.setAccountNo(accountHistory.getAccountNo());
        settRecord.setMerchantNo(accountHistory.getMerchantNo());
        if ("toBalance".equals(settleConfig.getSettleType())) {
            settRecord.setSettMode("toBalance");
        } else if ("toBankAccount".equals(settleConfig.getSettleType())) {

        } else {

        }
        settRecord.setSettStatus(SettRecordStatusEnum.REMIT_SUCCESS.getCode());
        settRecordMapper.insert(settRecord);
    }
}
