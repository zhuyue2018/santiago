package com.santiago.core.service.impl;

import com.santiago.core.entity.domain.MerchantPayInfo;
import com.santiago.core.mapper.MerchantPayInfoMapper;
import com.santiago.core.service.MerchantPayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantPayInfoServiceImpl implements MerchantPayInfoService {
    @Autowired
    MerchantPayInfoMapper merchantPayInfoMapper;
    @Override
    public MerchantPayInfo getByMerchantNo(String merchantNo) {
        MerchantPayInfo merchantPayInfoTemp = new MerchantPayInfo();
        merchantPayInfoTemp.setMerchantNo(merchantNo);
        return merchantPayInfoMapper.selectOne(merchantPayInfoTemp);
    }
}
