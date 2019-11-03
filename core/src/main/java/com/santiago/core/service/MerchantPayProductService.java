package com.santiago.core.service;

import java.math.BigDecimal;

public interface MerchantPayProductService {

    void create(String merchantNo, String payProductCode, BigDecimal feeRate);
}
