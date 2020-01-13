package com.santiago.core.wss;

import com.github.pagehelper.PageInfo;
import com.santiago.core.entity.dto.query.TradeOrderQuery;
import com.santiago.core.service.TradeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller(value = "/trade/order")
public class TradeOrderWss {
    @Autowired
    TradeOrderService tradeOrderService;

    public SimpleResponse query(String merchantNo, String orderNo) {
        return null;
    }

    @RequestMapping(value = "page###", method = RequestMethod.POST)
    public PageInfo page(TradeOrderQuery tradeOrderQuery) {
        PageInfo pageInfo =  tradeOrderService.pageTradeOrder(tradeOrderQuery);
        return pageInfo;
    }
}
