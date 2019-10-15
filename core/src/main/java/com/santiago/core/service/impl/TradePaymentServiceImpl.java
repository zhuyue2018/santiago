package com.santiago.core.service.impl;

import com.zhuyue.pay0929.commons.dto.req.ScanPayRequest;
import com.zhuyue.pay0929.commons.enums.ErrorCodeEnum;
import com.zhuyue.pay0929.commons.util.DateUtil;
import com.zhuyue.pay0929.core.entity.domain.RpTradePaymentOrder;
import com.zhuyue.pay0929.core.entity.domain.RpTradePaymentRecord;
import com.zhuyue.pay0929.core.entity.exception.TradeBizException;
import com.zhuyue.pay0929.core.mapper.RpTradePaymentOrderMapper;
import com.zhuyue.pay0929.core.mapper.RpTradePaymentRecordMapper;
import com.zhuyue.pay0929.core.service.BuildNoService;
import com.zhuyue.pay0929.core.service.TradePaymentService;
import com.zhuyue.pay0929.core.wss.ChannelInteractWss;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TradePaymentServiceImpl implements TradePaymentService {
    private static final Logger logger = LoggerFactory.getLogger(TradePaymentServiceImpl.class);
    @Autowired
    RpTradePaymentOrderMapper orderMapper;
    @Autowired
    ChannelInteractWss channelInteractWss;
    @Autowired
    BuildNoService buildNoService;
    @Autowired
    RpTradePaymentRecordMapper recordMapper;

    @Override
    public void preOrder(ScanPayRequest request) {
        String merchantNo = request.getField1();
        String orderNo = request.getOrderNo();
        assertOrderNotExist(merchantNo, orderNo);
        RpTradePaymentOrder order = createRpTradePaymentOrder(request);
        RpTradePaymentRecord tradePaymentRecord = createTradePaymentRecord(order);
        channelInteractWss.preOrder(tradePaymentRecord, request.getPayWayCode());
    }

    private void assertOrderNotExist(String merchantNo, String orderNo) {
        RpTradePaymentOrder orderTemp = new RpTradePaymentOrder();
        orderTemp.setMerchantNo(merchantNo);
        orderTemp.setMerchantOrderNo(orderNo);
        RpTradePaymentOrder existedOrder = orderMapper.selectOne(orderTemp);
        if (existedOrder != null) {
            throw TradeBizException.DUPLICATED_BIZ_NO;
        }
    }

    private RpTradePaymentOrder createRpTradePaymentOrder(ScanPayRequest request) {
        RpTradePaymentOrder rpTradePaymentOrder = new RpTradePaymentOrder();
        rpTradePaymentOrder.setGmtCreate(new Date());
        rpTradePaymentOrder.setGmtModified(new Date());
        rpTradePaymentOrder.setVersion("1.0.0");
        rpTradePaymentOrder.setProductName(request.getProductName());// 商品名称
        rpTradePaymentOrder.setMerchantOrderNo(request.getOrderNo());// 订单号
        rpTradePaymentOrder.setOrderAmount(new BigDecimal(request.getOrderPriceStr()));// 订单金额
        rpTradePaymentOrder.setMerchantNo(request.getField1());// 商户编号
        rpTradePaymentOrder.setMerchantName(request.getField2());// 商户名称
        try {
            Date orderTime = new SimpleDateFormat("yyyyMMddHHmmss").parse(request.getOrderTimeStr());
            rpTradePaymentOrder.setOrderTime(orderTime);// 下单时间
            Short orderPeriod = Short.valueOf(request.getOrderPeriodStr());
            rpTradePaymentOrder.setOrderPeriod(orderPeriod);// 订单有效期
            Date expireTime = DateUtil.addMinute(orderTime, orderPeriod);// 订单过期时间
            rpTradePaymentOrder.setExpireTime(expireTime);// 订单过期时间
        } catch (ParseException e) {
            logger.error("orderNo:{},orderTimeStr{},parse error", request.getOrderNo(), request.getOrderTimeStr());
            throw new TradeBizException(ErrorCodeEnum.PARAMS_ERROR.getCode(), "orderTimeStr parse error");
        }
        rpTradePaymentOrder.setOrderIp(request.getOrderIp());// 下单IP
        rpTradePaymentOrder.setOrderRefererUrl("");// 下单前页面
        rpTradePaymentOrder.setReturnUrl(request.getReturnUrl());// 页面通知地址
        rpTradePaymentOrder.setNotifyUrl(request.getNotifyUrl());// 后台通知地址
        rpTradePaymentOrder.setPayWayCode(request.getPayWayCode());// 支付通道编码
        rpTradePaymentOrder.setStatus("0");// 订单状态
        rpTradePaymentOrder.setFundIntoType(request.getField3());// 资金流入方向
        rpTradePaymentOrder.setRemark(request.getRemark());// 支付备注
        rpTradePaymentOrder.setField1(null);// 扩展字段1
        rpTradePaymentOrder.setField2(null);// 扩展字段2
        rpTradePaymentOrder.setField3(null);// 扩展字段3
        rpTradePaymentOrder.setField4(request.getField4());// 扩展字段4
        rpTradePaymentOrder.setField5(request.getField5());// 扩展字段5
        orderMapper.insert(rpTradePaymentOrder);
        return rpTradePaymentOrder;
    }


    private RpTradePaymentRecord createTradePaymentRecord(RpTradePaymentOrder order) {
        RpTradePaymentRecord tradePaymentRecord = new RpTradePaymentRecord();
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
        recordMapper.insert(tradePaymentRecord);
        return tradePaymentRecord;
    }

}
