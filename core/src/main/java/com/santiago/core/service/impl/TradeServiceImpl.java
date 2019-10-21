package com.santiago.core.service.impl;

import com.santiago.commons.enums.ErrorCodeEnum;
import com.santiago.commons.util.DateUtil;
import com.santiago.core.entity.domain.TradeOrder;
import com.santiago.core.entity.domain.TradeRecord;
import com.santiago.core.entity.dto.request.TradeRequest;
import com.santiago.core.entity.dto.response.PreOrderResponse;
import com.santiago.core.entity.exception.TradeBizException;
import com.santiago.core.mapper.TradeOrderMapper;
import com.santiago.core.mapper.TradeRecordMapper;
import com.santiago.core.service.BuildNoService;
import com.santiago.core.service.TradeService;
import com.santiago.core.wss.ChannelSendWss;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TradeServiceImpl implements TradeService {
    private static final Logger logger = LoggerFactory.getLogger(TradeServiceImpl.class);
    @Autowired
    ChannelSendWss channelSendWss;
    @Autowired
    TradeOrderMapper tradeOrderMapper;
    @Autowired
    BuildNoService buildNoService;
    @Autowired
    TradeRecordMapper tradeRecordMapper;

    @Override
    public PreOrderResponse preOrder(TradeRequest request) {
        String orderNo = request.getOrderNo();
        assertOrderNotExist(request.getMerchantNo(), orderNo);
        TradeOrder order = createTradeOrder(request);
        TradeRecord tradePaymentRecord = createTradePaymentRecord(order);
        return channelSendWss.preOrder(tradePaymentRecord, request.getPayProductCode());
    }

    private void assertOrderNotExist(String merchantNo, String orderNo) {
        TradeOrder orderTemp = new TradeOrder();
        orderTemp.setMerchantNo(merchantNo);
        orderTemp.setMerchantOrderNo(orderNo);
        TradeOrder existedOrder = tradeOrderMapper.selectOne(orderTemp);
        if (existedOrder != null) {
            throw TradeBizException.DUPLICATED_BIZ_NO;
        }
    }

    private TradeOrder createTradeOrder(TradeRequest request) {
        TradeOrder TradeOrder = new TradeOrder();
        TradeOrder.setGmtCreate(new Date());
        TradeOrder.setGmtModified(new Date());
        TradeOrder.setVersion("1.0.0");
        TradeOrder.setProductName(request.getProductName());// 商品名称
        TradeOrder.setMerchantOrderNo(request.getOrderNo());// 订单号
        TradeOrder.setOrderAmount(new BigDecimal(request.getOrderPriceStr()));// 订单金额
        TradeOrder.setMerchantNo(request.getMerchantNo());// 商户编号
        TradeOrder.setMerchantName(request.getField1());// 商户名称
        try {
            Date orderTime = new SimpleDateFormat("yyyyMMddHHmmss").parse(request.getOrderTimeStr());
            TradeOrder.setOrderTime(orderTime);// 下单时间
            Short orderPeriod = Short.valueOf(request.getOrderPeriodStr());
            TradeOrder.setOrderPeriod(orderPeriod);// 订单有效期
            Date expireTime = DateUtil.addMinute(orderTime, orderPeriod);// 订单过期时间
            TradeOrder.setExpireTime(expireTime);// 订单过期时间
        } catch (ParseException e) {
            logger.error("orderNo:{},orderTimeStr{},parse error", request.getOrderNo(), request.getOrderTimeStr());
            throw new TradeBizException(ErrorCodeEnum.PARAMS_ERROR.getCode(), "orderTimeStr parse error");
        }
        TradeOrder.setOrderIp(request.getOrderIp());// 下单IP
        TradeOrder.setOrderRefererUrl("");// 下单前页面
        TradeOrder.setReturnUrl(request.getReturnUrl());// 页面通知地址
        TradeOrder.setNotifyUrl(request.getNotifyUrl());// 后台通知地址
        TradeOrder.setPayProductCode(request.getPayProductCode());// 支付产品编码
        TradeOrder.setStatus("0");// 订单状态
        TradeOrder.setFundIntoType(request.getField2());// 资金流入方向
        TradeOrder.setRemark(request.getRemark());// 支付备注
        TradeOrder.setField1(null);// 扩展字段1
        TradeOrder.setField2(null);// 扩展字段2
        TradeOrder.setField3(null);// 扩展字段3
        TradeOrder.setField4(request.getField4());// 扩展字段4
        TradeOrder.setField5(request.getField5());// 扩展字段5
        tradeOrderMapper.insert(TradeOrder);
        return TradeOrder;
    }


    private TradeRecord createTradePaymentRecord(TradeOrder order) {
        TradeRecord tradePaymentRecord = new TradeRecord();
        tradePaymentRecord.setVersion("1.0.0");
        tradePaymentRecord.setGmtCreate(new Date());
        tradePaymentRecord.setGmtModified(new Date());
        tradePaymentRecord.setStatus("0");
        tradePaymentRecord.setProductName(order.getProductName());
        tradePaymentRecord.setMerchantOrderNo(order.getMerchantOrderNo());
        tradePaymentRecord.setTrxNo(buildNoService.buildTrxNo());
        tradePaymentRecord.setBankOrderNo(buildNoService.buildBankOrderNo());
        tradePaymentRecord.setMerchantName(order.getMerchantName());
        tradePaymentRecord.setMerchantNo(order.getMerchantNo());
        tradePaymentRecord.setPayerPayAmount(order.getOrderAmount());
        BigDecimal channelFeeRate = new BigDecimal("0.06");
        BigDecimal payerFee = order.getOrderAmount().multiply(channelFeeRate);
        tradePaymentRecord.setPayerFee(payerFee);
        BigDecimal orderAmount = order.getOrderAmount();
        tradePaymentRecord.setOrderAmount(orderAmount);
        tradePaymentRecord.setPlatIncome(orderAmount.subtract(payerFee));
        tradePaymentRecord.setFeeRate(channelFeeRate);
        tradePaymentRecord.setNotifyUrl("/channel/receiveNotify");
        tradePaymentRecord.setRemark(order.getRemark());
        tradeRecordMapper.insert(tradePaymentRecord);
        return tradePaymentRecord;
    }

}
