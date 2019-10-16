package com.santiago.core.service;

import com.santiago.core.entity.domain.MerchantPayInfo;

public interface MerchantPayInfoService {
    MerchantPayInfo getByMerchantNo(String merchantNo);
}
