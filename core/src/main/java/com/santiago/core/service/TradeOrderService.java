package com.santiago.core.service;

import com.github.pagehelper.PageInfo;
import com.santiago.core.entity.vo.TradePaymentOrderVO;

public interface TradeOrderService {
    PageInfo<TradePaymentOrderVO> pageTradePaymentOrderVO();
}
