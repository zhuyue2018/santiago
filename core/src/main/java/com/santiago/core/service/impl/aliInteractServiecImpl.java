package com.santiago.core.service.impl;

import com.zhuyue.pay0929.commons.dto.resp.SimpleResponse;
import com.zhuyue.pay0929.core.entity.domain.RpTradePaymentRecord;
import com.zhuyue.pay0929.core.service.ChannelInteractService;
import org.springframework.stereotype.Service;

@Service(value = "aliChannel")
public class aliInteractServiecImpl implements ChannelInteractService {
    @Override
    public SimpleResponse interact(RpTradePaymentRecord tradePaymentRecord) {
        return null;
    }
}
