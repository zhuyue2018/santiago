package com.santiago.gateway.controller;

import com.santiago.commons.annotation.LogParams;
import com.santiago.commons.dto.resp.UnionResp;
import com.santiago.commons.dto.resp.UnionResult;
import com.santiago.commons.enums.RespCodeEnum;
import com.santiago.order.api.dto.TradeOrderApi;
import com.santiago.order.api.dto.TradeRequest;
import com.santiago.order.api.exception.TradeBizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/trade")
public class TradeCtrl {
    private static final Logger logger = LoggerFactory.getLogger(TradeCtrl.class);
    @Autowired
    TradeOrderApi tradeOrderApi;

    @LogParams
    @RequestMapping("/preOrder")
    public UnionResp preOrder(@Valid @RequestBody TradeRequest request, BindingResult result) {
        if (result.hasErrors()) {
            String message = result.getFieldError().getDefaultMessage();
            throw new TradeBizException(RespCodeEnum.PARAMS_ERROR.getCode(), message);
        }
        UnionResult<String> result1 = tradeOrderApi.preOrder(request);
        return new UnionResp(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMsg());
    }
}