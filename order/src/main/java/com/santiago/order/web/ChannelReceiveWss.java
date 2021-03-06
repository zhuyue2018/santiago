package com.santiago.order.web;

import com.santiago.commons.enums.StatusEnum;
//import com.santiago.notify.entity.domain.NotifyRecord;
//import com.santiago.notify.wss.MerchantNotifyWss;
import com.santiago.order.entity.domain.TradeOrder;
import com.santiago.order.entity.domain.TradeRecord;
import com.santiago.order.api.dto.WeixinNotifyRequest;
import com.santiago.order.api.exception.ChannelReceiveBizException;
import com.santiago.order.mapper.TradeOrderMapper;
import com.santiago.order.mapper.TradeRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping(value = "/channel")
public class ChannelReceiveWss {
    private static final Logger logger = LoggerFactory.getLogger(ChannelReceiveWss.class);
    @Autowired
    TradeOrderMapper orderMapper;
    @Autowired
    TradeRecordMapper recordMapper;

    @RequestMapping("/receive/weixin")
    public void receive(WeixinNotifyRequest request) {
        TradeRecord tradePaymentRecordTemp = new TradeRecord();
        tradePaymentRecordTemp.setBankOrderNo(request.getBankOrderNo());
        TradeRecord tradePaymentRecord = recordMapper.selectOne(tradePaymentRecordTemp);
        TradeOrder tradePaymentOrderTemp = new TradeOrder();
        tradePaymentOrderTemp.setMerchantNo(tradePaymentRecord.getMerchantNo());
        tradePaymentOrderTemp.setMerchantOrderNo(tradePaymentRecord.getMerchantOrderNo());
        TradeOrder tradePaymentOrder = orderMapper.selectOne(tradePaymentOrderTemp);
        if (StatusEnum.INIT.getCode().equals(tradePaymentOrder.getStatus()) && StatusEnum.INIT.getCode().equals(tradePaymentRecord.getStatus())) {
            String status = null;
            if ("success".equals(request.getStatus())) {
                status = StatusEnum.SUCCESS.getCode();
            } else if ("fail".equals(request.getStatus())) {
                status = StatusEnum.FAILURE.getCode();
            } else {
                logger.error("bankOrderNo:{},status:{},不能识别的status", tradePaymentRecord.getBankOrderNo(), status);
            }
            tradePaymentOrder.setStatus(status);
            tradePaymentOrder.setGmtModified(new Date());
            orderMapper.updateByPrimaryKey(tradePaymentOrder);
            tradePaymentRecord.setStatus(status);
            tradePaymentRecord.setGmtModified(new Date());
            recordMapper.updateByPrimaryKey(tradePaymentRecord);
//            NotifyRecord notifyRecord = createNotifyRecord(tradePaymentOrder, status);
//            String result = notifyWss.doNotify(notifyRecord);
//            if ("000000".equals(result)) {
//                notifyRecord.setGmtModified(new Date());
//                notifyRecord.setNotifyTimes(1);
//                notifyRecord.setStatus(StatusEnum.SUCCESS.getCode());
//                notifyWss.updateRecord(notifyRecord);
//            }
        } else {
            logger.info("bankOrderNo:{},order或record状态不为0，不能更新", tradePaymentRecord.getBankOrderNo());
            throw ChannelReceiveBizException.TRADE_STATUS_ERROR;
        }
    }

//    private NotifyRecord createNotifyRecord(TradeOrder tradePaymentOrder, String orderStatus) {
//        NotifyRecord notifyRecord = new NotifyRecord();
//        notifyRecord.setVersion("1.0.0");
//        notifyRecord.setGmtCreate(new Date());
//        notifyRecord.setGmtModified(new Date());
//        notifyRecord.setNotifyTimes(0);
//        notifyRecord.setLimitNotifyTimes(20);
//        notifyRecord.setStatus("0");
//        notifyRecord.setUrl(tradePaymentOrder.getNotifyUrl());
//        notifyRecord.setMerchantNo(tradePaymentOrder.getMerchantNo());
//        notifyRecord.setMerchantOrderNo(tradePaymentOrder.getMerchantOrderNo());
//        notifyRecord.setOrderStatus(orderStatus);
//        notifyWss.insertNotifyRecord(notifyRecord);
//        return notifyRecord;
//    }
}
