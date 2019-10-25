package com.santiago.settlement.job;

import com.santiago.core.entity.domain.TradeOrder;
import com.santiago.core.entity.domain.TradeRecord;
import com.santiago.core.entity.dto.result.CountResult;
import com.santiago.core.entity.enums.PayProductEnum;
import com.santiago.core.service.BuildNoService;
import com.santiago.core.service.RedisService;
import com.santiago.core.service.TradeOrderService;
import com.santiago.settlement.entity.domain.Account;
import com.santiago.settlement.entity.domain.AccountCheckBatch;
import com.santiago.settlement.entity.result.RecCountResult;
import com.santiago.settlement.entity.result.RecDetailResult;
import com.santiago.settlement.mapper.SettDailyCollectMapper;
import com.santiago.settlement.service.AccountHistoryService;
import com.santiago.settlement.service.AccountService;
import com.santiago.settlement.service.MerchantSettleConfigService;
import com.santiago.settlement.service.SettleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
        Date date = new Date();
        AccountCheckBatch accountCheckBatch = accountCheckBatchService.getByDate(date);
        if (null != accountCheckBatch) {
            logger.warn("date:[{}], 已完成对账，不能再次发起自动对账", date);
            return;
        }
        accountCheckBatch = new AccountCheckBatch();
        accountCheckBatch.setBatchNo(buildNoService.buildReconciliationNo());
        PayProductEnum[] payProductEnums = PayProductEnum.values();
        for (int i = 0 ; i < payProductEnums.length ; i++) {
            PayProductEnum payProductEnum = payProductEnums[i];
            String code = payProductEnum.getCode();
            accountCheckBatch.setBankType(code);
            RecCountResult channelResult = downloadRecData(code, date);
            RecCountResult sysResult = orderService.count(code, date);
            if (asscertEqual(channelResult, sysResult)) {

            } else {
                RecDetailResult recDetailResult = recDetail(code, date);
            }
        }
    }

    private RecDetailResult recDetail(String code, Date date) {
        List<TradeRecord> sysList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (TradeRecord record : sysList) {
            sb.append(record.getBankOrderNo()).append(":").append(record.getOrderAmount()).append("|").append(record.getStatus());
            redisService.sSet("sys:record", sb.toString());
            sb.setLength(0);
        }
        List<TradeRecord> channelList = new ArrayList<>();
        for (TradeRecord record : channelList) {
            sb.append(record.getBankOrderNo()).append(":").append(record.getOrderAmount()).append("|").append(record.getStatus());
            redisService.sSet("chnl:record", sb.toString());
            sb.setLength(0);
        }
        Set<String> sysExceed = redisService.sdiff("sys:record", "chnl:record");
        Set<String> chnlExceed = redisService.sdiff("chnl:record", "sys:record");

        return null;
    }
}
