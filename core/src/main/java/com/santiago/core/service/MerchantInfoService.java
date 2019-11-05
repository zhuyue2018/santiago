package com.santiago.core.service;

import com.github.pagehelper.PageInfo;
import com.santiago.core.entity.domain.MerchantInfo;
import com.santiago.core.entity.vo.MerchantInfoVO;

import java.util.List;

public interface MerchantInfoService {
    void createDefault(MerchantInfo merchantInfo);
    List<MerchantInfo> list(MerchantInfo merchantInfo);


    PageInfo page();
}
