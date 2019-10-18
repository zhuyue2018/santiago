package com.santiago.portal.service;

import com.github.pagehelper.PageInfo;
import com.santiago.core.entity.dto.query.TradeOrderQuery;
import com.santiago.core.entity.vo.TradeOrderVO;

public interface OrderService {
    PageInfo<TradeOrderVO> pageTradeOrderVO(TradeOrderQuery tradeOrderQuery);
}
