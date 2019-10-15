package com.santiago.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuyue.pay0929.commons.util.DateUtil;
import com.zhuyue.pay0929.core.entity.domain.RpTradePaymentOrder;
import com.zhuyue.pay0929.core.entity.vo.TradePaymentOrderVO;
import com.zhuyue.pay0929.core.mapper.RpTradePaymentOrderMapper;
import com.zhuyue.pay0929.core.service.TradePaymentOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class TradePaymentOrderServiceImpl implements TradePaymentOrderService {
    @Autowired
    RpTradePaymentOrderMapper orderMapper;
    @Override
    public PageInfo<TradePaymentOrderVO> pageTradePaymentOrderVO() {
        PageHelper.startPage(1, 10);
        Example example = new Example(RpTradePaymentOrder.class);
        Example.Criteria criteria = example.createCriteria();
        List<RpTradePaymentOrder> orderList = orderMapper.selectByExample(example);
        List<TradePaymentOrderVO> voList = transfer2VOList(orderList);
        PageInfo<TradePaymentOrderVO> pageInfo = new PageInfo<>(voList);
        return pageInfo;
    }

    private List<TradePaymentOrderVO> transfer2VOList(List<RpTradePaymentOrder> orderList) {
        ArrayList<TradePaymentOrderVO> voList = new ArrayList<TradePaymentOrderVO>();
        orderList.forEach(order -> {
            voList.add(transfer2VO(order));
        });
        return voList;
    }

    private TradePaymentOrderVO transfer2VO(RpTradePaymentOrder order) {
        TradePaymentOrderVO vo = new TradePaymentOrderVO();
        vo.setGmtModified(DateUtil.formatDate(order.getGmtModified(), "yyyyMMddHHmmss"));
        vo.setMerchantOrderNo(order.getMerchantNo());
        vo.setOrderAmount(String.valueOf(order.getOrderAmount()));
        vo.setMerchantName(order.getMerchantName());
        vo.setOrderTime(DateUtil.formatDate(order.getOrderTime(), "yyyyMMddHHmmss"));
        vo.setPayWayName(order.getPayWayName());
        vo.setRemark(order.getRemark());
        return vo;
    }

}
