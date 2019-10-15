package com.santiago.core.service;

import com.zhuyue.pay0929.commons.dto.req.ScanPayRequest;

public interface TradePaymentService {
    void preOrder(ScanPayRequest request);
}
