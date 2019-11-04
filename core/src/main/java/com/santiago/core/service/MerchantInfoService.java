package com.santiago.core.service;

import com.santiago.core.entity.domain.MerchantInfo;

import java.util.List;

public interface MerchantInfoService {
    void createDefault(MerchantInfo merchantInfo);
    List<MerchantInfo> list(MerchantInfo merchantInfo);


}
