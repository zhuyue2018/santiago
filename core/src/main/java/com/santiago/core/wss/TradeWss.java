package com.santiago.core.wss;

import com.santiago.commons.util.EncryptUtil;
import com.santiago.core.entity.domain.MerchantPayConfig;
import com.santiago.core.entity.domain.MerchantPayInfo;
import com.santiago.core.entity.dto.request.TradeRequest;
import com.santiago.core.entity.exception.TradeBizException;
import com.santiago.core.entity.exception.UserBizException;
import com.santiago.core.service.MerchantPayConfigService;
import com.santiago.core.service.MerchantPayInfoService;
import com.santiago.core.service.TradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TradeWss {
    private static final Logger logger = LoggerFactory.getLogger(TradeWss.class);
    @Autowired
    MerchantPayConfigService merchantPayConfigService;
    @Autowired
    MerchantPayInfoService merchantPayInfoService;
    @Autowired
    TradeService tradeService;

    @PostMapping(value = "/preOrder")
    public void preOrder(@RequestBody TradeRequest request) {
        MerchantPayConfig merchantPayConfig = merchantPayConfigService.getByMerchantNo(request.getMerchantNo());
        assertNotnull(merchantPayConfig);
        validIp(request.getOrderIp());
        MerchantPayInfo merchantPayInfo = merchantPayInfoService.getByMerchantNo(request.getMerchantNo());
        assertNotnull(merchantPayInfo);
        validSign(request, merchantPayInfo.getMd5Key());
        request.setField1(merchantPayConfig.getMerchantNo());
        request.setField2(merchantPayInfo.getMerchantName());
        request.setField3(merchantPayConfig.getFundIntoType());
        tradeService.preOrder(request);
    }

    private void assertNotnull(MerchantPayInfo merchantPayInfo) {

    }


    private void validIp(String orderIp) {
        if (false) {
            throw TradeBizException.IP_ERROR;
        }
    }

    private void assertNotnull(MerchantPayConfig merchantPayConfig) {
        if (merchantPayConfig == null) {
            throw UserBizException.USER_PAY_CONFIG_ERRPR;
        }
    }

    private void validSign(TradeRequest request, String paySecret) {
        try {
            String sign = sign(paySecret, request.getMerchantNo(), request.getProductName(), request.getOrderNo());
            if (!sign.equals(request.getSign())) {
                throw TradeBizException.SIGN_ERROR;
            }
        } catch (Exception e) {
            logger.warn("orderNo:{},系统构造签名异常", request.getOrderNo());
            throw TradeBizException.SYSTEM_ERROR;
        }
    }

    private String sign(String paySecret, String... params) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < params.length ; i ++) {
            sb.append(params[i]);
        }
        sb.append(paySecret);
        return EncryptUtil.encodeMD5String(sb.toString());
    }
}
