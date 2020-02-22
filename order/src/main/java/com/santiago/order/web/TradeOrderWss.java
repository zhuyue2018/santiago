package com.santiago.order.web;

import com.github.pagehelper.PageInfo;
import com.santiago.commons.dto.resp.UnionResp;
import com.santiago.order.api.dto.TradeOrderApi;
import com.santiago.order.api.dto.TradeOrderQuery;
import com.santiago.order.service.TradeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller(value = "/trade/order")
public class TradeOrderWss implements TradeOrderApi {
    @Autowired
    TradeOrderService tradeOrderService;

    public UnionResp query(String merchantNo, String orderNo) {
        return null;
    }

    @RequestMapping(value = "/page###", method = RequestMethod.POST)
    public PageInfo page(TradeOrderQuery tradeOrderQuery) {
        PageInfo pageInfo =  tradeOrderService.pageTradeOrder(tradeOrderQuery);
        return pageInfo;
    }
}
