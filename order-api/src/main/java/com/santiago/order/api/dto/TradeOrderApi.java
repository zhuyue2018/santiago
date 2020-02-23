package com.santiago.order.api.dto;

import com.github.pagehelper.PageInfo;
import com.santiago.commons.dto.resp.UnionResp;
import com.santiago.commons.dto.resp.UnionResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "orders")
public interface TradeOrderApi {

//    UnionResp query(String merchantNo, String orderNo);

    @RequestMapping(value = "/", method = RequestMethod.POST)
    PageInfo page(TradeOrderQuery tradeOrderQuery);

    UnionResult<String> preOrder(TradeRequest request);
}
