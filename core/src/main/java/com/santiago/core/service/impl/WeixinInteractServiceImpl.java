package com.santiago.core.service.impl;

import com.santiago.core.entity.domain.TradeRecord;
import com.santiago.core.entity.dto.response.PreOrderResponse;
import com.santiago.core.service.ChannelInteractService;
import org.springframework.stereotype.Service;

@Service(value = "001")
public class WeixinInteractServiceImpl implements ChannelInteractService {
    @Override
    public PreOrderResponse interact(TradeRecord tradeRecord) {
        PreOrderResponse response = new PreOrderResponse("000000", "weixin");
        response.setCodeUrl("testcodeurl");
        return response;
    }
}
