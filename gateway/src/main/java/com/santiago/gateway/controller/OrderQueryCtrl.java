package com.santiago.gateway.controller;

import com.santiago.commons.dto.resp.SimpleResponse;
import com.santiago.core.wss.TradePaymentOrderWss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/order")
public class OrderQueryCtrl {
    @Autowired
    TradePaymentOrderWss orderWss;

    @GetMapping(value = "/{merchantNo}/{orderNo}")
    public SimpleResponse query(@PathVariable(value = "merchantNo") String merchantNo,
                                @PathVariable(value = "orderNo") String orderNo) {
        return orderWss.query(merchantNo, orderNo);
    }
}
