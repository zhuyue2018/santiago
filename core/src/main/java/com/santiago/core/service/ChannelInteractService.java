package com.santiago.core.service;

import com.santiago.core.entity.domain.TradeRecord;
import com.santiago.core.entity.dto.response.PreOrderResponse;

public interface ChannelInteractService {
    PreOrderResponse interact(TradeRecord tradeRecord);
}
