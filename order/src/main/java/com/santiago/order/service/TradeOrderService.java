package com.santiago.order.service;

import com.github.pagehelper.PageInfo;
import com.santiago.order.api.dto.TradeOrderQuery;
import com.santiago.order.entity.domain.TradeOrder;
import org.joda.time.DateTime;

import java.util.List;

public interface TradeOrderService {
    PageInfo pageTradeOrder(TradeOrderQuery tradeOrderQuery);
    List<TradeOrder> listRecData(String code, DateTime billDate);

    List<TradeOrder> listByStatus(String status);
}
