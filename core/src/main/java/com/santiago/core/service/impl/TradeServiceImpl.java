package com.santiago.core.service.impl;

import com.santiago.commons.enums.ErrorCodeEnum;
import com.santiago.commons.enums.SecurityRatingEnum;
import com.santiago.commons.enums.StatusEnum;
import com.santiago.commons.enums.VersionEnum;
import com.santiago.commons.util.DateUtil;
import com.santiago.commons.util.EncryptUtil;
import com.santiago.core.entity.domain.MerchantPayInfo;
import com.santiago.core.entity.domain.TradeOrder;
import com.santiago.core.entity.domain.TradeRecord;
import com.santiago.core.entity.dto.request.TradeRequest;
import com.santiago.core.entity.dto.response.PreOrderResponse;
import com.santiago.core.entity.exception.TradeBizException;
import com.santiago.core.entity.exception.UserBizException;
import com.santiago.core.mapper.TradeOrderMapper;
import com.santiago.core.mapper.TradeRecordMapper;
import com.santiago.core.service.BuildNoService;
import com.santiago.core.service.ChannelSendService;
import com.santiago.core.service.MerchantPayInfoService;
import com.santiago.core.service.TradeService;
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
    ChannelSendService channelSendService;
    @Autowired
    TradeOrderMapper tradeOrderMapper;
    @Autowired
    BuildNoService buildNoService;
    @Autowired
    TradeRecordMapper tradeRecordMapper;
    @Autowired
    MerchantPayInfoService merchantPayInfoService;

    @Override
    public PreOrderResponse preOrder(TradeRequest request) {
        MerchantPayInfo merchantPayInfo = merchantPayInfoService.getByMerchantNo(request.getMerchantNo());
        assertNotnull(merchantPayInfo);
        validate(request, merchantPayInfo.getSecurityRating(), merchantPayInfo.getMerchantServerIp(), merchantPayInfo.getMd5Key());
        request.setField1(merchantPayInfo.getMerchantName());
        String orderNo = request.getOrderNo();
        assertOrderNotExist(request.getMerchantNo(), orderNo);
        TradeOrder order = createTradeOrder(request);
        TradeRecord tradePaymentRecord = createTradePaymentRecord(order);
        return channelSendService.preOrder(tradePaymentRecord, request.getPayProductCode());
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
        TradeOrder.setVersion(VersionEnum.INIT.getCode());
        TradeOrder.setProductName(request.getProductName());// 商品名称
        TradeOrder.setMerchantOrderNo(request.getOrderNo());// 订单号
        TradeOrder.setOrderAmount(new BigDecimal(request.getOrderPriceStr()));// 订单金额
        TradeOrder.setMerchantNo(request.getMerchantNo());// 商户编号
        TradeOrder.setMerchantName(request.getField1());// 商户名称
        TradeOrder.setTrxNo(buildNoService.buildTrxNo());
        TradeOrder.setBankOrderNo(buildNoService.buildBankOrderNo());
        try {
            Date orderTime = new SimpleDateFormat("yyyyMMddHHmmss").parse(request.getOrderTimeStr());
            TradeOrder.setOrderTime(orderTime);// 下单时间
            Short orderPeriod = Short.valueOf(request.getOrderPeriodStr());
            TradeOrder.setOrderPeriod(orderPeriod);// 订单有效期
            Date expireTime = DateUtil.addMinute(orderTime, orderPeriod);// 订单过期时间
            TradeOrder.setExpireTime(expireTime);// 订单过期时间
        } catch (ParseException e) {
            logger.error("orderNo:{}, orderTimeStr{}, parse error", request.getOrderNo(), request.getOrderTimeStr());
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
        tradePaymentRecord.setVersion(VersionEnum.INIT.getCode());
        tradePaymentRecord.setGmtCreate(new Date());
        tradePaymentRecord.setGmtModified(new Date());
        tradePaymentRecord.setStatus(StatusEnum.INIT.getCode());
        tradePaymentRecord.setProductName(order.getProductName());
        tradePaymentRecord.setMerchantOrderNo(order.getMerchantOrderNo());
        tradePaymentRecord.setTrxNo(order.getTrxNo());
        tradePaymentRecord.setBankOrderNo(order.getBankOrderNo());
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

    private void validate(TradeRequest request, String securityRating, String merchantServerIp, String md5Key) {
        if (SecurityRatingEnum.SIGN.getCode().equals(securityRating)) {
            validateIp(request.getOrderIp(), merchantServerIp);
            validateSign(request, md5Key);
            return;
        }
        if (SecurityRatingEnum.IP.getCode().equals(securityRating)) {
            validateIp(request.getOrderIp(), merchantServerIp);
            return;
        }
        if (SecurityRatingEnum.NONE.getCode().equals(securityRating)) {
            return;
        } else {
            throw UserBizException.SECURITY_RATING_ERROR;
        }
    }

    private void assertNotnull(MerchantPayInfo merchantPayInfo) {
        if (merchantPayInfo == null) {
            throw UserBizException.USER_PAY_CONFIG_ERROR;
        }
    }

    private void validateIp(String orderIp, String merchantServerIp) {
        if (merchantServerIp.indexOf(orderIp) < 0) {
            throw TradeBizException.IP_ERROR;
        }
    }

    private void validateSign(TradeRequest request, String md5Key) {
        String sign = "";
        try {
            sign = sign(md5Key, request.getMerchantNo(), request.getProductName(), request.getOrderNo());
        } catch (Exception e) {
            logger.warn("orderNo:{},系统构造签名异常", request.getOrderNo());
            throw TradeBizException.SYSTEM_ERROR;
        }
        if (!sign.equals(request.getSign())) {
            throw TradeBizException.SIGN_ERROR;
        }
    }

    private String sign(String md5Key, String... params) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < params.length; i++) {
            sb.append(params[i]);
        }
        sb.append(md5Key);
        return EncryptUtil.encodeMD5String(sb.toString());
    }

}
