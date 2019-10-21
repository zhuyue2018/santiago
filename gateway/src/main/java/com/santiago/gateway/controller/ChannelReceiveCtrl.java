package com.santiago.gateway.controller;

import com.santiago.commons.enums.ErrorCodeEnum;
import com.santiago.commons.util.JsonUtil;
import com.santiago.core.entity.domain.TradeOrder;
import com.santiago.core.entity.domain.TradeRecord;
import com.santiago.core.entity.dto.WeixinNotifyRequest;
import com.santiago.core.entity.exception.TradeBizException;
import com.santiago.core.mapper.TradeRecordMapper;
import com.santiago.core.wss.ChannelReceiveWss;
import com.santiago.notify.entity.domain.NotifyRecord;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping(value = "/channel/receive")
public class ChannelReceiveCtrl {
    private static final Logger logger = LoggerFactory.getLogger(ChannelReceiveCtrl.class);
    @Autowired
    ChannelReceiveWss channelReceiveWss;

    @RequestMapping("/weixin")
    public String receiveNotify(@Valid @RequestBody WeixinNotifyRequest request, BindingResult result) {
        logger.info("收到回调通知，request:{}", JsonUtil.create().objectToJson(request));
        if (result.hasErrors()) {
            String message = result.getFieldError().getDefaultMessage();
            throw new TradeBizException(ErrorCodeEnum.PARAMS_ERROR.getCode(), message);
        }
        channelReceiveWss.receive(request);



        return "success";
    }
}
