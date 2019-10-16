package com.santiago.core.wss;

import com.santiago.commons.dto.resp.SimpleResponse;
import com.santiago.core.entity.domain.TradeOrder;
import com.santiago.core.entity.domain.TradeRecord;
import com.santiago.core.entity.dto.WeixinNotifyRequest;
import com.santiago.core.mapper.TradeOrderMapper;
import com.santiago.core.mapper.TradeRecordMapper;
import com.santiago.core.service.ChannelInteractService;
import com.santiago.core.service.SpringContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController()
public class ChannelSendWss {
    private static final Logger logger = LoggerFactory.getLogger(ChannelSendWss.class);
    @Autowired
    TradeOrderMapper orderMapper;
    @Autowired
    TradeRecordMapper recordMapper;
    @Autowired
    MerchantNotifyWss notifyWss;

    @PostMapping("/channel/preOrder")
    public SimpleResponse preOrder(TradeRecord tradeRecord, String payWayCode) {
        ChannelInteractService channel = null;
        if ("001".equals(payWayCode)) {
            channel = (ChannelInteractService) SpringContextUtil.getBean("weixinChannel");
        }
        if ("002".equals(payWayCode)) {
            channel = (ChannelInteractService)SpringContextUtil.getBean("aliChannel");
        }
        return channel.interact(tradeRecord);
    }

    @RequestMapping("/channel/weixin/receiveNotify")
    public String receiveNotify(@RequestBody WeixinNotifyRequest request) {
        logger.info("收到回调通知，request:{}", JsonUtil.create().objectToJson(request));
        String bankOrderNo = request.getBankOrderNo();
        if (StringUtils.isEmpty(bankOrderNo)) {
            logger.error("bankOrderNo空");
            return "error";
        }
        TradeRecord tradePaymentRecordTemp = new TradeRecord();
        tradePaymentRecordTemp.setBankOrderNo(bankOrderNo);
        TradeRecord tradePaymentRecord = recordMapper.selectOne(tradePaymentRecordTemp);
        TradeOrder tradePaymentOrderTemp = new TradeOrder();
        tradePaymentOrderTemp.setMerchantNo(tradePaymentRecord.getMerchantNo());
        tradePaymentOrderTemp.setMerchantOrderNo(tradePaymentRecord.getMerchantOrderNo());
        TradeOrder tradePaymentOrder = orderMapper.selectOne(tradePaymentOrderTemp);
        if ("success".equals(request.getStatus())) {
            if ("0".equals(tradePaymentOrder.getStatus()) && "0".equals(tradePaymentRecord.getStatus())) {
                tradePaymentOrder.setStatus("1");
                tradePaymentOrder.setGmtModified(new Date());
                orderMapper.updateByPrimaryKey(tradePaymentOrder);
                tradePaymentRecord.setStatus("1");
                tradePaymentRecord.setGmtModified(new Date());
                recordMapper.updateByPrimaryKey(tradePaymentRecord);
                NotifyRecord notifyRecord = createNotifyRecord(tradePaymentOrder, "1");
                String result = notifyWss.doNotify(notifyRecord);
                if ("000000".equals(result)) {
                    notifyRecord.setGmtModified(new Date());
                    notifyRecord.setNotifyTimes(1);
                    notifyRecord.setStatus("1");
                    notifyWss.updateRecord(notifyRecord);
                }
            } else {
                logger.info("bankOrderNo:{},状态不为0，不能更新", tradePaymentRecord.getBankOrderNo());
                return "success";
            }
        }

        return "success";
    }

    private NotifyRecord createNotifyRecord(TradeOrder tradePaymentOrder, String orderStatus) {
        NotifyRecord notifyRecord = new NotifyRecord();
        notifyRecord.setVersion("1.0.0");
        notifyRecord.setGmtCreate(new Date());
        notifyRecord.setGmtModified(new Date());
        notifyRecord.setNotifyTimes(0);
        notifyRecord.setLimitNotifyTimes(20);
        notifyRecord.setStatus("0");
        notifyRecord.setUrl(tradePaymentOrder.getNotifyUrl());
        notifyRecord.setMerchantNo(tradePaymentOrder.getMerchantNo());
        notifyRecord.setMerchantOrderNo(tradePaymentOrder.getMerchantOrderNo());
        notifyRecord.setOrderStatus(orderStatus);
        notifyWss.insertNotifyRecord(notifyRecord);
        return notifyRecord;
    }


}
