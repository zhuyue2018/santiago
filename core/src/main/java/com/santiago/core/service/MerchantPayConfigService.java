package com.santiago.core.service;


import com.santiago.core.entity.domain.MerchantPayConfig;

public interface MerchantPayConfigService {
    MerchantPayConfig getByMerchantNo(String merchantNo);

    void insert(MerchantPayConfig merchantPayConfig);
}
