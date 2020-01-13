package com.santiago.core.service.impl;

import com.santiago.core.entity.domain.TradeRecord;
import com.santiago.core.entity.dto.response.PreOrderResponse;
import com.santiago.core.service.ChannelInteractService;
import org.springframework.stereotype.Service;

@Service(value = "002")
public class aliInteractServiecImpl implements ChannelInteractService {
    @Override
    public PreOrderResponse interact(TradeRecord tradeRecord) {
        return null;
    }
}
