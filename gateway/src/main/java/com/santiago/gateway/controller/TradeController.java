package com.santiago.gateway.controller;

import com.santiago.commons.annotation.LogParams;
import com.santiago.commons.dto.resp.SimpleResponse;
import com.santiago.commons.enums.ErrorCodeEnum;
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
public class TradeController {
    private static final Logger logger = LoggerFactory.getLogger(TradeController.class);
    @Autowired
    TradeWss tradeWss;

    @LogParams
    @RequestMapping("/preOrder")
    public SimpleResponse preOrder(@Valid @RequestBody TradeRequest request, BindingResult result) {
        if (result.hasErrors()) {
            String message = result.getFieldError().getDefaultMessage();
            throw new TradeBizException(ErrorCodeEnum.PARAMS_ERROR.getCode(), message);
        }
        tradeWss.preOrder(request);
        return new SimpleResponse(ErrorCodeEnum.SUCCESS.getCode(), ErrorCodeEnum.SUCCESS.getMsg());
    }
}