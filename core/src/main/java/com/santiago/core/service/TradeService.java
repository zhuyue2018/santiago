package com.santiago.core.service;

import com.santiago.core.entity.dto.request.TradeRequest;
import com.santiago.core.entity.dto.response.PreOrderResponse;

public interface TradeService {
    PreOrderResponse preOrder(TradeRequest request);
}
