package com.santiago.settlement.job;

import com.santiago.commons.enums.StatusEnum;
import com.santiago.commons.enums.VersionEnum;
import com.santiago.core.entity.domain.Account;
import com.santiago.core.entity.domain.TradeOrder;
import com.santiago.core.entity.domain.TradeRecord;
import com.santiago.core.entity.enums.PayProductEnum;
import com.santiago.core.service.*;
import com.santiago.core.wss.AccountWss;
import com.santiago.core.wss.MerchantInfoWss;
import com.santiago.settlement.entity.domain.AccountCheckBatch;
import com.santiago.settlement.entity.domain.AccountHistory;
import com.santiago.core.entity.result.RecCountResult;
import com.santiago.core.entity.result.RecDetailResult;
import com.santiago.core.entity.result.DiffRecord;
import com.santiago.settlement.mapper.SettDailyCollectMapper;
import com.santiago.settlement.service.*;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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
//    @Autowired
//    MerchantSettleConfigService merchantSettleConfigService;
    @Autowired
    AccountHistoryService accountHistoryService;
    @Autowired
    SettleService settleService;
    @Autowired
    SettDailyCollectMapper settDailyCollectMapper;
    @Autowired
    TradeOrderService orderService;
    @Autowired
    AccountWss accountWss;
    @Autowired
    AccountCheckBatchService accountCheckBatchService;
    @Autowired
    BuildNoService buildNoService;
    @Autowired
    RedisService redisService;
    @Autowired
    MerchantInfoWss merchantInfoWss;


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
        List<Account> accountList = accountWss.listAll();
        for (Account acc : accountList) {
            rec(acc);
            AccountHistory accountHistory = new AccountHistory();
            accountHistory.setGmtCreate(new Date());
            accountHistory.setGmtModified(new Date());
            accountHistory.setRecDate(DateTime.now().toString("yyyyMMdd"));
            accountHistory.setAccountNo(acc.getAccountNo());
            accountHistory.setSettStatus(StatusEnum.SUCCESS.getCode());
            accountHistoryService.insert(accountHistory);
        }
    }

    private void rec(Account acc) {
        DateTime billDate = DateTime.now().minusDays(1);
        AccountCheckBatch accountCheckBatch = accountCheckBatchService.getByBillDate(billDate);
        if (null != accountCheckBatch) {
            logger.warn("date:[{}], 已完成对账，不能再次发起自动对账", billDate);
        }
        accountCheckBatch = new AccountCheckBatch();
        accountCheckBatch.setBatchNo(buildNoService.buildReconciliationNo());
        PayProductEnum[] payProductEnums = PayProductEnum.values();
        for (int i = 0 ; i < payProductEnums.length ; i++) { // 根据支付产品下载对账文件
            PayProductEnum payProductEnum = payProductEnums[i];
            String code = payProductEnum.getCode();
            accountCheckBatch.setBankType(code);
            RecCountResult channelResult = downloadRecCountData(code, billDate);
            List<TradeOrder> tradeOrderList = orderService.listRecData(code, billDate);
            RecCountResult sysResult = calculateRecSumData(tradeOrderList);
            if (!assertEqual(channelResult, sysResult)) { // 对比汇总数据，没问题直接返回，有问题则核对明细
                RecDetailResult recDetailResult = recDetail(tradeOrderList, code, billDate);

            }
        }
    }

    private synchronized RecCountResult calculateRecSumData(List<TradeOrder> tradeOrderList) {
        BigDecimal count = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (TradeOrder o : tradeOrderList) {
            count = count.add(new BigDecimal(1));
            totalAmount = totalAmount.add(o.getOrderAmount());
        }
        return new RecCountResult(count.toString(), totalAmount.toString());
    }

    private boolean assertEqual(RecCountResult channelResult, RecCountResult sysResult) {
        if (channelResult.getCount().equals(sysResult.getCount()) && channelResult.getTotalAmount().equals(sysResult.getTotalAmount())) {
            return true;
        }
        return false;
    }

    private RecCountResult downloadRecCountData(String code, DateTime billDate) { // todo
        return null;
    }


    private RecDetailResult recDetail(List<TradeOrder> tradeOrderList, String code, DateTime billDate) {
        StringBuilder sb = new StringBuilder();
        for (TradeOrder record : tradeOrderList) {
            sb.append(record.getBankOrderNo()).append("|").append(record.getOrderAmount()).append("|").append(record.getStatus());
            redisService.sSet("sys:record", sb.toString());
            sb.setLength(0);
        }
        List<TradeRecord> channelList = downloadRecDetailData(code, billDate);
        for (TradeRecord record : channelList) {
            sb.append(record.getBankOrderNo()).append("|").append(record.getOrderAmount()).append("|").append(record.getStatus());
            redisService.sSet("chnl:record", sb.toString());
            sb.setLength(0);
        }
        Set<String> sysExceed = redisService.sdiff("sys:record", "chnl:record");
        Set<String> chnlExceed = redisService.sdiff("chnl:record", "sys:record");
        return new RecDetailResult(toDiffRecord(sysExceed), toDiffRecord(chnlExceed));
    }

    private List<TradeRecord> downloadRecDetailData(String code, DateTime billDate) { // todo
        return null;
    }

    private List<DiffRecord> toDiffRecord(Set<String> sysExceed) {
        List<DiffRecord> list = new ArrayList<>();
        Iterator<String> iterator = sysExceed.iterator();
        if (iterator.hasNext()) {
            String[] split = iterator.next().split("|");
            list.add(new DiffRecord(split[0], split[1], split[2]));
        }
        return list;
    }
}
