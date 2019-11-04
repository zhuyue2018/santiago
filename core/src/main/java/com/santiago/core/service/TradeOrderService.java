package com.santiago.core.service;

import com.github.pagehelper.PageInfo;
import com.santiago.core.entity.domain.TradeOrder;
import com.santiago.core.entity.dto.query.TradeOrderQuery;
import org.joda.time.DateTime;

import java.util.List;

public interface TradeOrderService {
    PageInfo pageTradeOrder(TradeOrderQuery tradeOrderQuery);
    List<TradeOrder> listRecData(String code, DateTime billDate);
}
