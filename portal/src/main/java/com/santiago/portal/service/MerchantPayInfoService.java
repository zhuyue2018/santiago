package com.santiago.portal.service;


import com.santiago.portal.entity.domain.MerchantPayInfo;

public interface MerchantPayInfoService {
    MerchantPayInfo getByMerchantNo(String merchantNo);

    void createDefault(String merchantNo, String merchantName, String s);
}
