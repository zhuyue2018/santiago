package com.santiago.core.service.impl;

import com.santiago.core.entity.domain.Message;
import com.santiago.core.entity.domain.TradeOrder;
import com.santiago.core.service.TradeOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.DelayQueue;

@Component
public class TradeOrderDelayCloseManager {
    private static final Logger logger = LoggerFactory.getLogger(TradeOrderDelayCloseManager.class);
    private final DelayQueue<Message> closeQueue;
    public TradeOrderDelayCloseManager() throws InterruptedException {
        this.closeQueue = new DelayQueue<>();
    }
    @Autowired
    ThreadPoolTaskExecutor executor;
    @Autowired
    TradeOrderService tradeOrderService;

    @PostConstruct
    public void init() throws InterruptedException {
        List<TradeOrder> unPayTradeOrderList = tradeOrderService.listByStatus("1");
        unPayTradeOrderList.forEach(tradeOrder -> {
            this.insert(new Message(tradeOrder.getId(), tradeOrder.getStatus(), tradeOrder.getGmtCreate()));
        });
        while (true) {
            Message order = this.closeQueue.take();
            executor.execute(() -> {
                logger.info("查询数据库中订单状态，serialNo:{}", order.getId());
                logger.info("查询渠道订单状态，serialNo:{}", order.getId());
                logger.info("关闭，serialNo:{}", order.getId());
            });
        }
    }

    public void insert(Message message) {
        this.closeQueue.add(message);
    }
}
