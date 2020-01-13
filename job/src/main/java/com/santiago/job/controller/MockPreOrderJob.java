package com.santiago.job.controller;


import com.santiago.api.CoreApi;
import com.santiago.commons.util.DateUtil;
import com.santiago.commons.util.HttpUtils;
import com.santiago.job.entity.domain.TradeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@EnableScheduling
public class MockPreOrderJob {
    private static final Logger logger = LoggerFactory.getLogger(MockPreOrderJob.class);
    @Autowired
    CoreApi coreApi;
//    @Autowired
//    DefaultUidGenerator defaultUidGenerator;
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    @Scheduled(cron = "0/5 * * * * ?")
    private void preOrder() {
        logger.info("执行静态定时任务时间: " + LocalDateTime.now());
        TradeRequest tradeRequest = new TradeRequest();
        tradeRequest.setMerchantNo("88882019102410001136");
        Long uid = coreApi.getUid();
        logger.info("uid" + uid);
        tradeRequest.setProductName("macbookpro");
        tradeRequest.setOrderNo("mock" + uid);
        tradeRequest.setOrderPriceStr("0.01");
        tradeRequest.setOrderIp("127.0.0.2");
        tradeRequest.setPayProductCode("001");
        tradeRequest.setOrderTimeStr(DateUtil.getCurrentDateStr(DateUtil.YYYYMMDDHHMMSS));
        tradeRequest.setReturnUrl("nothing");
        tradeRequest.setNotifyUrl("nothing");
        tradeRequest.setRemark("remark");
        tradeRequest.setSign("sign");
        logger.info(HttpUtils.sendSimpleJsonPost("http://127.0.0.1:8080/trade/preorder", JsonUtil.create().objectToJson(tradeRequest)));
    }
}