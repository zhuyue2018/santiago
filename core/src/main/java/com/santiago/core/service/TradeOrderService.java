package com.santiago.core.service;

import com.github.pagehelper.PageInfo;
import com.santiago.core.entity.domain.TradeOrder;
import com.santiago.core.entity.dto.query.TradeOrderQuery;

public interface TradeOrderService {
    PageInfo pageTradeOrder(TradeOrderQuery tradeOrderQuery);
}
