package com.santiago.core.wss;

import com.zhuyue.pay0929.commons.dto.resp.SimpleResponse;
import com.zhuyue.pay0929.commons.util.JsonUtil;
import com.zhuyue.pay0929.core.entity.domain.RpTradePaymentOrder;
import com.zhuyue.pay0929.core.entity.domain.RpTradePaymentRecord;
import com.zhuyue.pay0929.core.entity.dto.WeixinNotifyRequest;
import com.zhuyue.pay0929.core.mapper.RpTradePaymentOrderMapper;
import com.zhuyue.pay0929.core.mapper.RpTradePaymentRecordMapper;
import com.zhuyue.pay0929.core.service.ChannelInteractService;
import com.zhuyue.pay0929.core.service.SpringContextUtil;
import com.zhuyue.pay0929.notify.entity.domain.RpNotifyRecord;
import com.zhuyue.pay0929.notify.wss.NotifyWss;
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
public class ChannelInteractWss {
    private static final Logger logger = LoggerFactory.getLogger(ChannelInteractWss.class);
    @Autowired
    RpTradePaymentOrderMapper orderMapper;
    @Autowired
    RpTradePaymentRecordMapper recordMapper;
    @Autowired
    NotifyWss notifyWss;

    @PostMapping("/channel/preOrder")
    public SimpleResponse preOrder(RpTradePaymentRecord tradePaymentRecord, String payWayCode) {
        ChannelInteractService channel = null;
        if ("001".equals(payWayCode)) {
            channel = (ChannelInteractService) SpringContextUtil.getBean("weixinChannel");
        }
        if ("002".equals(payWayCode)) {
            channel = (ChannelInteractService)SpringContextUtil.getBean("aliChannel");
        }
        return channel.interact(tradePaymentRecord);
    }

    @RequestMapping("/channel/weixin/receiveNotify")
    public String receiveNotify(@RequestBody WeixinNotifyRequest request) {
        logger.info("收到回调通知，request:{}", JsonUtil.create().objectToJson(request));
        String bankOrderNo = request.getBankOrderNo();
        if (StringUtils.isEmpty(bankOrderNo)) {
            logger.error("bankOrderNo空");
            return "error";
        }
        RpTradePaymentRecord tradePaymentRecordTemp = new RpTradePaymentRecord();
        tradePaymentRecordTemp.setBankOrderNo(bankOrderNo);
        RpTradePaymentRecord tradePaymentRecord = recordMapper.selectOne(tradePaymentRecordTemp);
        RpTradePaymentOrder tradePaymentOrderTemp = new RpTradePaymentOrder();
        tradePaymentOrderTemp.setMerchantNo(tradePaymentRecord.getMerchantNo());
        tradePaymentOrderTemp.setMerchantOrderNo(tradePaymentRecord.getMerchantOrderNo());
        RpTradePaymentOrder tradePaymentOrder = orderMapper.selectOne(tradePaymentOrderTemp);
        if ("success".equals(request.getStatus())) {
            if ("0".equals(tradePaymentOrder.getStatus()) && "0".equals(tradePaymentRecord.getStatus())) {
                tradePaymentOrder.setStatus("1");
                tradePaymentOrder.setGmtModified(new Date());
                orderMapper.updateByPrimaryKey(tradePaymentOrder);
                tradePaymentRecord.setStatus("1");
                tradePaymentRecord.setGmtModified(new Date());
                recordMapper.updateByPrimaryKey(tradePaymentRecord);
                RpNotifyRecord notifyRecord = createNotifyRecord(tradePaymentOrder, "1");
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

    private RpNotifyRecord createNotifyRecord(RpTradePaymentOrder tradePaymentOrder, String orderStatus) {
        RpNotifyRecord notifyRecord = new RpNotifyRecord();
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
