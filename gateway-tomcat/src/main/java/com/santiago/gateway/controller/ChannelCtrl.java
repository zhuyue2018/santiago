package com.santiago.gateway.controller;

import com.santiago.api.AccountApi;
import com.santiago.commons.enums.RespCodeEnum;
import com.santiago.core.entity.dto.request.WeixinNotifyRequest;
import com.santiago.core.entity.exception.TradeBizException;
import com.santiago.core.wss.ChannelReceiveWss;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/channel")
public class ChannelCtrl {
    private static final Logger logger = LoggerFactory.getLogger(ChannelCtrl.class);
    @Autowired
    ChannelReceiveWss channelReceiveWss;
    @Autowired
    AccountApi accountApi;

    @RequestMapping("/receive/weixin")
    public BaseResponse receiveNotify(@Valid @RequestBody WeixinNotifyRequest request, BindingResult result) {
        logger.info("收到回调通知，request:{}", JsonUtil.create().objectToJson(request));
        if (result.hasErrors()) {
            String message = result.getFieldError().getDefaultMessage();
            throw new TradeBizException(RespCodeEnum.PARAMS_ERROR.getCode(), message);
        }
        channelReceiveWss.receive(request);
        accountApi.account();
        SimpleResponse simpleResponse = new SimpleResponse("success", "接受回调通知成功");
        return simpleResponse;
    }
}
