package com.santiago.portal.service.impl;

import com.github.pagehelper.PageInfo;
import com.santiago.core.wss.TradeOrderWss;
import com.santiago.core.entity.dto.query.TradeOrderQuery;
import com.santiago.portal.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2019-10-18 08:51
 **/
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    TradeOrderWss tradeOrderWss;

    @Override
    public PageInfo pageTradeOrderVO(TradeOrderQuery tradeOrderQuery) {
        PageInfo orderVOPage = tradeOrderWss.page(tradeOrderQuery);
        return orderVOPage;
    }
}
