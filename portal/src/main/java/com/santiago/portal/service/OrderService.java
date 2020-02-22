package com.santiago.portal.service;

import com.github.pagehelper.PageInfo;
import com.santiago.order.api.dto.TradeOrderQuery;
import com.santiago.order.api.dto.TradeOrderVO;

public interface OrderService {
    PageInfo<TradeOrderVO> pageTradeOrderVO(TradeOrderQuery tradeOrderQuery);
}
