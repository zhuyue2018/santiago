package com.santiago.core.service;

import com.santiago.commons.dto.resp.SimpleResponse;
import com.santiago.core.entity.domain.TradeRecord;

public interface ChannelInteractService {
    SimpleResponse interact(TradeRecord tradeRecord);
}
