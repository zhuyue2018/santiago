package com.santiago.core.service;

import com.santiago.core.entity.dto.request.TradeRequest;

public interface TradeService {
    void preOrder(TradeRequest request);
}
