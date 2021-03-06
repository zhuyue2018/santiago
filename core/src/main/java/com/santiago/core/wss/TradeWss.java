package com.santiago.core.wss;

import com.santiago.core.entity.dto.response.PreOrderResponse;
import com.santiago.core.service.TradeService;
import com.santiago.order.api.dto.TradeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TradeWss {
    private static final Logger logger = LoggerFactory.getLogger(TradeWss.class);

    @Autowired
    TradeService tradeService;

    @PostMapping(value = "/preOrder")
    public PreOrderResponse preOrder(@RequestBody TradeRequest request) {
        // 校验
        return tradeService.preOrder(request);
    }


}
