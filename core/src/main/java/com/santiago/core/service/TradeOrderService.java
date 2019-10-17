package com.santiago.core.service;

import com.github.pagehelper.PageInfo;
import com.santiago.core.entity.vo.TradeOrderVO;

public interface TradeOrderService {
    PageInfo<TradeOrderVO> pageTradePaymentOrderVO();
}
