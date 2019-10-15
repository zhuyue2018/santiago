//package com.santiago.gateway.controller;
//
//import com.santiago.commons.dto.resp.SimpleResponse;
//import com.santiago.commons.enums.ErrorCodeEnum;
//import com.santiago.commons.util.JsonUtil;
//import com.santiago.core.entity.domain.RpUserPayConfig;
//import com.santiago.core.entity.exception.TradeBizException;
//import com.santiago.core.entity.exception.UserBizException;
//import com.santiago.core.service.UserPayConfigService;
//import com.santiago.core.wss.ScanPayWss;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//
//@RestController
//@RequestMapping(value = "/scanPay")
//public class ScanPayController {
//    private static final Logger logger = LoggerFactory.getLogger(ScanPayController.class);
//    @Autowired
//    ScanPayWss scanPayWss;
//    @Autowired
//    UserPayConfigService userPayConfigService;
//
//    @RequestMapping("/preOrder")
//    public SimpleResponse preOrder(@Valid @RequestBody ScanPayRequest request, BindingResult result) {
//        logger.info("扫码支付,接收参数:{}", JsonUtil.create().objectToJson(request));
//        if (result.hasErrors()) {
//            String message = result.getFieldError().getDefaultMessage();
//            throw new TradeBizException(ErrorCodeEnum.PARAMS_ERROR.getCode(), message);
//        }
//        RpUserPayConfig rpUserPayConfig = userPayConfigService.getByPayKey(request.getPayKey());
//        assertNotnull(rpUserPayConfig);
//        validIp(request.getOrderIp());
//        validSign(request, rpUserPayConfig.getPaySecret());
//        request.setField1(rpUserPayConfig.getUserNo());
//        request.setField2(rpUserPayConfig.getUserName());
//        request.setField3(rpUserPayConfig.getFundIntoType());
//        return new SimpleResponse(ErrorCodeEnum.SUCCESS.getCode(), ErrorCodeEnum.SUCCESS.getMsg());
//    }
//
//    private void validIp(String orderIp) {
//        if (false) {
//            throw TradeBizException.IP_ERROR;
//        }
//    }
//
//    private void assertNotnull(RpUserPayConfig rpUserPayConfig) {
//        if (rpUserPayConfig == null) {
//            throw UserBizException.USER_PAY_CONFIG_ERRPR;
//        }
//    }
//
//    private void validSign(ScanPayRequest request, String paySecret) {
//        try {
//            String sign = sign(paySecret, request.getPayKey(), request.getProductName(), request.getOrderNo());
//            if (!sign.equals(request.getSign())) {
//                throw TradeBizException.SIGN_ERROR;
//            }
//        } catch (Exception e) {
//            logger.warn("orderNo:{},系统构造签名异常", request.getOrderNo());
//            throw TradeBizException.SYSTEM_ERROR;
//        }
//    }
//
//    private String sign(String paySecret, String... params) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0 ; i < params.length ; i ++) {
//            sb.append(params[i]);
//        }
//        sb.append(paySecret);
//        return EncryptUtil.encodeMD5String(sb.toString());
//    }
//
//}