package com.santiago.core.service;

import com.santiago.core.entity.domain.Message;
import com.santiago.core.entity.dto.request.TradeRequest;
import com.santiago.core.entity.dto.response.PreOrderResponse;
import org.springframework.scheduling.annotation.Async;

public interface TradeService {
    PreOrderResponse preOrder(TradeRequest request);

    @Async
    void delayClose(Message msg);
}
