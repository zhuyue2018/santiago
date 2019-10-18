package com.santiago.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.santiago.core.entity.domain.TradeOrder;
import com.santiago.core.entity.dto.query.TradeOrderQuery;
import com.santiago.core.entity.vo.TradeOrderVO;
import com.santiago.core.mapper.TradeOrderMapper;
import com.santiago.core.service.TradeOrderService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class TradeOrderServiceImpl implements TradeOrderService {
    @Autowired
    TradeOrderMapper orderMapper;
    @Override
    public PageInfo pageTradeOrder(TradeOrderQuery tradeOrderQuery) {
        PageHelper.startPage(tradeOrderQuery.getPageNum(), tradeOrderQuery.getPageSize());
        Example example = new Example(TradeOrder.class);
        Example.Criteria criteria = example.createCriteria();
        List<TradeOrder> orderList = orderMapper.selectByExample(example);
//        PageInfo tradeOrderPageInfo = new PageInfo<>(orderList);
        List<TradeOrderVO> voList = transfer2VOList(orderList);
        PageInfo<TradeOrderVO> tradeOrderPageInfo = new PageInfo<>(voList);
        tradeOrderPageInfo.setList(voList);
        return tradeOrderPageInfo;
    }

    private List<TradeOrderVO> transfer2VOList(List<TradeOrder> orderList) {
        ArrayList<TradeOrderVO> voList = new ArrayList<TradeOrderVO>();
        orderList.forEach(order -> {
            voList.add(transfer2VO(order));
        });
        return voList;
    }

    private TradeOrderVO transfer2VO(TradeOrder order) {
        TradeOrderVO vo = new TradeOrderVO();
        vo.setGmtModified(new DateTime(order.getGmtModified()).plusHours(8).toString("yyyy-MM-dd HH:mm:ss"));
        vo.setMerchantOrderNo(order.getMerchantNo());
        vo.setOrderAmount(String.valueOf(order.getOrderAmount()));
        vo.setMerchantName(order.getMerchantName());
        vo.setOrderTime(new DateTime(order.getGmtModified()).plusHours(8).toString("yyyy-MM-dd HH:mm:ss"));
        vo.setRemark(order.getRemark());
        return vo;
    }

}
