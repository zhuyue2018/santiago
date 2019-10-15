package com.santiago.core.wss;

import com.zhuyue.pay0929.commons.dto.req.ScanPayRequest;
import com.zhuyue.pay0929.commons.dto.resp.SimpleResponse;
import com.zhuyue.pay0929.commons.enums.ErrorCodeEnum;
import com.zhuyue.pay0929.commons.util.DateUtil;
import com.zhuyue.pay0929.commons.util.JsonUtil;
import com.zhuyue.pay0929.core.entity.domain.RpTradePaymentOrder;
import com.zhuyue.pay0929.core.entity.domain.RpTradePaymentRecord;
import com.zhuyue.pay0929.core.entity.exception.TradeBizException;
import com.zhuyue.pay0929.core.mapper.RpTradePaymentOrderMapper;
import com.zhuyue.pay0929.core.mapper.RpTradePaymentRecordMapper;
import com.zhuyue.pay0929.core.service.BuildNoService;
import com.zhuyue.pay0929.core.service.TradePaymentOrderService;
import com.zhuyue.pay0929.core.service.TradePaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
public class ScanPayWss {
    private static final Logger logger = LoggerFactory.getLogger(ScanPayWss.class);
    @Autowired
    TradePaymentService tradePaymentService;

    @PostMapping(value = "/preOrder")
    public void preOrder(@RequestBody ScanPayRequest request) {
        tradePaymentService.preOrder(request);
    }
}
