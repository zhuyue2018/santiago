package com.santiago.core.service.impl;

import com.santiago.commons.dto.resp.SimpleResponse;
import com.santiago.core.entity.domain.TradeRecord;
import com.santiago.core.service.ChannelInteractService;
import org.springframework.stereotype.Service;

@Service(value = "weixinChannel")
public class WeixinInteractServiceImpl implements ChannelInteractService {
    @Override
    public SimpleResponse interact(TradeRecord tradeRecord) {
        return new SimpleResponse("000000", "weixin");
    }


}
