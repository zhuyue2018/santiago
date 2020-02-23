package com.santiago.gateway.controller;

import com.santiago.commons.dto.resp.UnionResp;
import com.santiago.commons.dto.resp.UnionResult;
import com.santiago.commons.enums.RespCodeEnum;
import com.santiago.commons.util.JsonUtil;
import com.santiago.order.api.dto.ChannelReceiveApi;
import com.santiago.order.api.dto.WeixinNotifyRequest;
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
    @Autowired
    ChannelReceiveApi channelReceiveApi;
    private static final Logger logger = LoggerFactory.getLogger(ChannelCtrl.class);


    @RequestMapping("/receive/weixin")
    public UnionResp receiveNotify(WeixinNotifyRequest request) {
        logger.info("收到回调通知，request:{}", JsonUtil.obj2JsonStrExcludeNull(null));
        UnionResult<Object> result = channelReceiveApi.receive(request);
        if ("000000".equals(result.getCode())) {
            return new UnionResp("success", "接受回调通知成功");
        }
        return new UnionResp("999999", "");
    }
}
