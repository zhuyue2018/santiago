package com.santiago.gateway.controller;

import com.santiago.commons.annotation.LogParams;
import com.santiago.commons.dto.resp.UnionResp;
import com.santiago.commons.enums.RespCodeEnum;
import com.santiago.core.entity.dto.request.TradeRequest;
import com.santiago.core.entity.exception.TradeBizException;
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
@RequestMapping(value = "/trade")
public class TradeCtrl {
    private static final Logger logger = LoggerFactory.getLogger(TradeCtrl.class);
    @Autowired
    TradeWss tradeWss;

    @LogParams
    @RequestMapping("/preOrder")
    public UnionResp preOrder(@Valid @RequestBody TradeRequest request, BindingResult result) {
        if (result.hasErrors()) {
            String message = result.getFieldError().getDefaultMessage();
            throw new TradeBizException(RespCodeEnum.PARAMS_ERROR.getCode(), message);
        }
        tradeWss.preOrder(request);
        return new UnionResp(RespCodeEnum.SUCCESS.getCode(), RespCodeEnum.SUCCESS.getMsg());
    }
}