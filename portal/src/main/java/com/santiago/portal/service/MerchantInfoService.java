package com.santiago.portal.service;

import com.github.pagehelper.PageInfo;
import com.santiago.portal.entity.domain.MerchantInfo;

import java.util.List;

public interface MerchantInfoService {
    void createDefault(MerchantInfo merchantInfo);
    List<MerchantInfo> list(MerchantInfo merchantInfo);


    PageInfo page();
}
