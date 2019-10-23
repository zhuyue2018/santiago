package com.santiago.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.santiago.commons.enums.StatusEnum;
import com.santiago.core.entity.domain.TradeOrder;
import com.santiago.core.entity.dto.query.TradeOrderQuery;
import com.santiago.core.entity.vo.TradeOrderVO;
import com.santiago.core.mapper.TradeOrderMapper;
import com.santiago.core.service.TradeOrderService;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TradeOrderServiceImpl implements TradeOrderService {
    @Autowired
    TradeOrderMapper orderMapper;
    @Override
    public PageInfo pageTradeOrder(TradeOrderQuery tradeOrderQuery) {
        PageHelper.startPage(tradeOrderQuery.getPageNum(), tradeOrderQuery.getPageSize());
        Example example = new Example(TradeOrder.class);
        example.setOrderByClause("gmt_create desc");
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(tradeOrderQuery.getMerchantNo())) {
            criteria.andEqualTo("merchantNo", tradeOrderQuery.getMerchantNo());
        }
        if (StringUtils.isNotEmpty(tradeOrderQuery.getMerchantOrderNo())) {
            criteria.andEqualTo("merchantOrderNo", tradeOrderQuery.getMerchantOrderNo());
        }
        if (StringUtils.isNotEmpty(tradeOrderQuery.getPayProductCode())) {
            criteria.andEqualTo("payProductCode", tradeOrderQuery.getPayProductCode());
        }
        if (StringUtils.isNotEmpty(tradeOrderQuery.getTrxNo())) {
            criteria.andEqualTo("trxNo", tradeOrderQuery.getTrxNo());
        }
        if (StringUtils.isNotEmpty(tradeOrderQuery.getStatus())) {
            criteria.andEqualTo("status", tradeOrderQuery.getStatus());
        }
        if (StringUtils.isNotEmpty(tradeOrderQuery.getBeginTime())) {
            criteria.andGreaterThanOrEqualTo("gmtCreate", tradeOrderQuery.getBeginTime());
        }
        if (StringUtils.isNotEmpty(tradeOrderQuery.getEndTime())) {
            criteria.andLessThanOrEqualTo("gmtCreate", tradeOrderQuery.getEndTime());
        }
        List<TradeOrder> orderList = orderMapper.selectByExample(example);
        PageInfo tradeOrderPageInfo = new PageInfo<>(orderList);
        List<TradeOrderVO> voList = transfer2VOList(orderList);
//        PageInfo<TradeOrderVO> tradeOrderPageInfo = new PageInfo<>(voList);
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
        vo.setOrderTime(new DateTime(order.getGmtModified()).toString("yyyy-MM-dd HH:mm:ss"));
        vo.setGmtCreate(new DateTime(order.getGmtCreate()).toString("yyyy-MM-dd HH:mm:ss"));
        vo.setGmtModified(new DateTime(order.getGmtModified()).toString("yyyy-MM-dd HH:mm:ss"));
        vo.setMerchantName(order.getMerchantName());
        vo.setProductName(order.getProductName());
        vo.setStatus(StatusEnum.getMsgByCode(order.getStatus()));
        vo.setMerchantOrderNo(order.getMerchantOrderNo());
        vo.setTrxNo(order.getTrxNo());
        vo.setPayProductName(order.getPayProductCode());
        vo.setOrderAmount(String.valueOf(order.getOrderAmount()));
        vo.setRemark(order.getRemark());
        return vo;
    }

}
