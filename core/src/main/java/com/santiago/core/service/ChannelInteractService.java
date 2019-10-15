package com.santiago.core.service;

import com.zhuyue.pay0929.commons.dto.resp.SimpleResponse;
import com.zhuyue.pay0929.core.entity.domain.RpTradePaymentRecord;

public interface ChannelInteractService {
    SimpleResponse interact(RpTradePaymentRecord tradePaymentRecord);
}
