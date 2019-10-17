package com.santiago.core.wss;

import com.github.pagehelper.PageInfo;
import com.santiago.commons.dto.resp.SimpleResponse;
import com.santiago.core.entity.vo.TradeOrderVO;
import com.santiago.core.service.TradeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller(value = "/core/order")
public class TradeOrderWss {
    @Autowired
    TradeOrderService tradePaymentOrderService;

    public SimpleResponse query(String merchantNo, String orderNo) {
        return null;
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public PageInfo<TradeOrderVO> page() {
        PageInfo<TradeOrderVO> pageInfo =  tradePaymentOrderService.pageTradePaymentOrderVO();
        return pageInfo;
    }
}
