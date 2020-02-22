package com.santiago.core.service;

import com.santiago.core.entity.dto.response.PreOrderResponse;
import com.santiago.order.api.dto.TradeRequest;

public interface TradeService {
    PreOrderResponse preOrder(TradeRequest request);

}
