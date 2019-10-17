package com.santiago.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.santiago.commons.util.DateUtil;
import com.santiago.core.entity.domain.TradeOrder;
import com.santiago.core.entity.vo.TradeOrderVO;
import com.santiago.core.mapper.TradeOrderMapper;
import com.santiago.core.service.TradeOrderService;
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
    public PageInfo<TradeOrderVO> pageTradePaymentOrderVO() {
        PageHelper.startPage(1, 10);
        Example example = new Example(TradeOrder.class);
        Example.Criteria criteria = example.createCriteria();
        List<TradeOrder> orderList = orderMapper.selectByExample(example);
        List<TradeOrderVO> voList = transfer2VOList(orderList);
        PageInfo<TradeOrderVO> pageInfo = new PageInfo<>(voList);
        return pageInfo;
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
        vo.setGmtModified(DateUtil.formatDate(order.getGmtModified(), "yyyyMMddHHmmss"));
        vo.setMerchantOrderNo(order.getMerchantNo());
        vo.setOrderAmount(String.valueOf(order.getOrderAmount()));
        vo.setMerchantName(order.getMerchantName());
        vo.setOrderTime(DateUtil.formatDate(order.getOrderTime(), "yyyyMMddHHmmss"));
        vo.setRemark(order.getRemark());
        return vo;
    }

}
