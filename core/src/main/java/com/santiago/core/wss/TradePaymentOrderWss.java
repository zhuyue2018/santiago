package com.santiago.core.wss;

import com.github.pagehelper.PageInfo;
import com.santiago.commons.dto.resp.SimpleResponse;
import com.santiago.core.entity.vo.TradePaymentOrderVO;
import com.santiago.core.service.TradePaymentOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller(value = "/core/order")
public class TradePaymentOrderWss {
    @Autowired
    TradePaymentOrderService tradePaymentOrderService;

    public SimpleResponse query(String merchantNo, String orderNo) {
        return null;
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public PageInfo<TradePaymentOrderVO> page() {
        PageInfo<TradePaymentOrderVO> pageInfo =  tradePaymentOrderService.pageTradePaymentOrderVO();
        return pageInfo;
    }
}
