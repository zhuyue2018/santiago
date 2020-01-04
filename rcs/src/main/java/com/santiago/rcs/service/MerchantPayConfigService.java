package com.santiago.rcs.service;


import com.santiago.rcs.entity.domain.MerchantPayConfig;

public interface MerchantPayConfigService {
    MerchantPayConfig getByMerchantNo(String merchantNo);
    void insert(MerchantPayConfig merchantPayConfig);

    void createDefault(String merchantNo, String securityRate, String merchantServerIp);
}
