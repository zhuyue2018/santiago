package com.santiago.job.controller;


import com.baidu.fsg.uid.impl.DefaultUidGenerator;
import com.santiago.api.NotifyApi;
import com.santiago.commons.util.DateUtil;
import com.santiago.core.entity.dto.request.TradeRequest;
import com.santiago.job.service.JobRecordService;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Component
@EnableScheduling
public class MockPreOrderJob {
    @Autowired
    DefaultUidGenerator defaultUidGenerator;
    private static final Logger logger = LoggerFactory.getLogger(MockPreOrderJob.class);
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    @Scheduled(cron = "0/5 * * * * ?")
    private void preOrder() {
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
        TradeRequest tradeRequest = new TradeRequest();
        tradeRequest.setMerchantNo("88882019102410001136");
        tradeRequest.setProductName("macbookpro");
        tradeRequest.setOrderNo("mock" + defaultUidGenerator.getUID());
        tradeRequest.setOrderPriceStr("0.01");
        tradeRequest.setOrderIp("127.0.0.2");
        tradeRequest.setPayProductCode("001");
        tradeRequest.setOrderTimeStr(DateUtil.getCurrentDateStr(DateUtil.YYYYMMDDHHMMSS));
        tradeRequest.setReturnUrl("nothing");
        tradeRequest.setNotifyUrl("nothing");
        tradeRequest.setRemark("remark");
        tradeRequest.setSign("sign");

    }
}