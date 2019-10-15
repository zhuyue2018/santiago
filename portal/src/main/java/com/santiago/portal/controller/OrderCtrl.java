package com.santiago.portal.controller;

import com.github.pagehelper.PageInfo;
import com.zhuyue.pay0929.core.entity.vo.TradePaymentOrderVO;
import com.zhuyue.pay0929.core.wss.OrderWss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller(value = "/portal/order")
public class OrderCtrl {

    @Autowired
    OrderWss orderWss;

    @RequestMapping(value = {"", "/list"})
    @ResponseBody
    public PageInfo<TradePaymentOrderVO> page() {
        return orderWss.page();
    }
}
