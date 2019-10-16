package com.santiago.gateway.controller;

import com.santiago.commons.dto.resp.SimpleResponse;
import com.santiago.commons.enums.ErrorCodeEnum;
import com.santiago.commons.util.JsonUtil;
import com.santiago.core.entity.dto.request.TradeRequest;
import com.santiago.core.entity.exception.TradeBizException;
import com.santiago.core.service.MerchantPayConfigService;
import com.santiago.core.wss.TradeWss;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/scanPay")
public class ScanPayController {
    private static final Logger logger = LoggerFactory.getLogger(ScanPayController.class);
    @Autowired
    TradeWss tradeWss;
    @Autowired
    MerchantPayConfigService merchantPayConfigService;

    @RequestMapping("/preOrder")
    public SimpleResponse preOrder(@Valid @RequestBody TradeRequest request, BindingResult result) {
        logger.info("扫码支付,接收参数:{}", JsonUtil.create().objectToJson(request));
        if (result.hasErrors()) {
            String message = result.getFieldError().getDefaultMessage();
            throw new TradeBizException(ErrorCodeEnum.PARAMS_ERROR.getCode(), message);
        }
        tradeWss.preOrder(request);
        return new SimpleResponse(ErrorCodeEnum.SUCCESS.getCode(), ErrorCodeEnum.SUCCESS.getMsg());
    }


}