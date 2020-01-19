package com.santiago.settlement.job;

import com.santiago.api.RcsApi;
import com.santiago.api.dto.MerchantSettleConfig;
import com.santiago.commons.enums.StatusEnum;
import com.santiago.settlement.entity.domain.AccountHistory;
import com.santiago.settlement.entity.domain.SettDailyCollect;
import com.santiago.settlement.mapper.SettDailyCollectMapper;
import com.santiago.settlement.service.AccountHistoryService;
import com.santiago.settlement.service.SettleService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2019-10-25 11:00
 **/
@Component
@EnableScheduling
public class AutoSettleJob {
    private static final Logger logger = LoggerFactory.getLogger(AutoSettleJob.class);
    @Autowired
    RcsApi rcsApi;
    @Autowired
    AccountHistoryService accountHistoryService;
    @Autowired
    SettleService settleService;
    @Autowired
    SettDailyCollectMapper settDailyCollectMapper;

    @Scheduled(cron = "0 0 1 * * ?")
    public void autoSettle() {
        SettDailyCollect settDailyCollect = new SettDailyCollect();
        MerchantSettleConfig merchantSettleConfig = new MerchantSettleConfig();
        merchantSettleConfig.setIsAutoSettle("0");
        List<MerchantSettleConfig> list = rcsApi.listMerchantSettleConfig(merchantSettleConfig);
        for (MerchantSettleConfig settleConfig : list ) {
            Integer settlePeriod = settleConfig.getSettlePeriod();
            String date = DateTime.now().minusDays(settlePeriod).toString("yyyyMMdd");
            AccountHistory accountHistory = accountHistoryService.getByMerchantNoAndRecDate(String.valueOf(settleConfig.getMerchantNo()), date);
            if (StatusEnum.SUCCESS.getCode().equals(accountHistory.getSettStatus())) {
                logger.warn("merchantNo:[{}], recDate:[{}], 已结算，勿重复结算", String.valueOf(settleConfig.getMerchantNo()), date);
                continue;
            }
            settleService.settle(settleConfig, accountHistory);
            accountHistory.setSettStatus(StatusEnum.SUCCESS.getCode());
            accountHistory.setGmtModified(new Date());
            accountHistoryService.update(accountHistory);
        }
        settDailyCollect.setSettStatus(StatusEnum.SUCCESS.getCode());
        settDailyCollectMapper.insert(settDailyCollect);
    }
}
