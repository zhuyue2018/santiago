package com.santiago.gateway.controller;

import com.santiago.commons.dto.resp.UnionResp;
import com.santiago.commons.enums.RespCodeEnum;
import com.santiago.commons.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/channel")
public class ChannelCtrl {
    private static final Logger logger = LoggerFactory.getLogger(ChannelCtrl.class);

//    @Autowired
//    AccountApi accountApi;

    @RequestMapping("/receive/weixin")
    public UnionResp receiveNotify() {
        logger.info("收到回调通知，request:{}", JsonUtil.obj2JsonStrExcludeNull(null));
//        if (result.hasErrors()) {
//            String message = result.getFieldError().getDefaultMessage();
//            throw new TradeBizException(RespCodeEnum.PARAMS_ERROR.getCode(), message);
//        }
//        channelReceiveWss.receive(request);
//        accountApi.accounting();
        return new UnionResp("success", "接受回调通知成功");
    }
}
