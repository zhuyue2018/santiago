package com.santiago.settlement.job;

import com.santiago.commons.enums.StatusEnum;
import com.santiago.commons.enums.VersionEnum;
import com.santiago.core.entity.domain.Account;
import com.santiago.core.service.BuildNoService;
import com.santiago.core.service.RedisService;
import com.santiago.core.service.TradeOrderService;
import com.santiago.settlement.entity.domain.AccountCheckBatch;
import com.santiago.settlement.entity.domain.AccountHistory;
import com.santiago.settlement.mapper.SettDailyCollectMapper;
import com.santiago.settlement.service.*;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2019-10-25 11:00
 **/
@Component
@EnableScheduling
public class AutoRecJob {
    private static final Logger logger = LoggerFactory.getLogger(AutoRecJob.class);
    @Autowired
    MerchantSettleConfigService merchantSettleConfigService;
    @Autowired
    AccountHistoryService accountHistoryService;
    @Autowired
    SettleService settleService;
    @Autowired
    SettDailyCollectMapper settDailyCollectMapper;
    @Autowired
    TradeOrderService orderService;
    @Autowired
    AccountService accountService;
    @Autowired
    AccountCheckBatchService accountCheckBatchService;
    @Autowired
    BuildNoService buildNoService;
    @Autowired
    RedisService redisService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void autoRec() {
        rec();
    }

    private void rec() {
        AccountCheckBatch accountCheckBatch = new AccountCheckBatch();
        accountCheckBatch.setGmtCreate(new Date());
        accountCheckBatch.setGmtModified(new Date());
        accountCheckBatch.setVersion(VersionEnum.INIT.getCode());
        accountCheckBatch.setStatus("2");
        accountCheckBatch.setBatchNo(buildNoService.buildReconciliationNo());
        accountCheckBatchService.insert(accountCheckBatch);
        List<Account> accountList = accountService.listAll();
        for (Account acc : accountList) {
            AccountHistory accountHistory = new AccountHistory();
            accountHistory.setGmtCreate(new Date());
            accountHistory.setGmtModified(new Date());
            accountHistory.setRecDate(DateTime.now().toString("yyyyMMdd"));
            accountHistory.setAccountNo(acc.getAccountNo());
            accountHistory.setSettStatus(StatusEnum.SUCCESS.getCode());
            accountHistoryService.insert(accountHistory);
        }
    }

//    private void rec() {
//        Date date = new Date();
//        AccountCheckBatch accountCheckBatch = accountCheckBatchService.getByDate(date);
//        if (null != accountCheckBatch) {
//            logger.warn("date:[{}], 已完成对账，不能再次发起自动对账", date);
//            return;
//        }
//        accountCheckBatch = new AccountCheckBatch();
//        accountCheckBatch.setBatchNo(buildNoService.buildReconciliationNo());
//        PayProductEnum[] payProductEnums = PayProductEnum.values();
//        for (int i = 0 ; i < payProductEnums.length ; i++) { // 根据支付产品下载对账文件
//            PayProductEnum payProductEnum = payProductEnums[i];
//            String code = payProductEnum.getCode();
//            accountCheckBatch.setBankType(code);
//            RecCountResult channelResult = downloadRecData(code, date);
//            RecCountResult sysResult = orderService.count(code, date);
//            if (asscertEqual(channelResult, sysResult)) { // 对比汇总数据，没问题直接返回，有问题则核对明细
//                return;
//            } else {
//                RecDetailResult recDetailResult = recDetail(code, date);
//            }
//        }
//    }
//
//
//    private RecDetailResult recDetail(String code, Date date) {
//        List<TradeRecord> sysList = new ArrayList<>();
//        StringBuilder sb = new StringBuilder();
//        for (TradeRecord record : sysList) {
//            sb.append(record.getBankOrderNo()).append(":").append(record.getOrderAmount()).append("|").append(record.getStatus());
//            redisService.sSet("sys:record", sb.toString());
//            sb.setLength(0);
//        }
//        List<TradeRecord> channelList = new ArrayList<>();
//        for (TradeRecord record : channelList) {
//            sb.append(record.getBankOrderNo()).append(":").append(record.getOrderAmount()).append("|").append(record.getStatus());
//            redisService.sSet("chnl:record", sb.toString());
//            sb.setLength(0);
//        }
//        Set<String> sysExceed = redisService.sdiff("sys:record", "chnl:record");
//        Set<String> chnlExceed = redisService.sdiff("chnl:record", "sys:record");
//        List<DiffRecord> sysExceedList = toDiffRecord(sysExceed);
//        List<DiffRecord> sysExceedList = toDiffRecord(sysExceed);
//        Iterator<String> iterator = sysExceed.iterator();
//        if (iterator.hasNext()) {
//            /**
//             *
//             */
//        }
//        return null;
//    }
}
