package com.santiago.core.service.impl;

import com.santiago.core.entity.domain.MerchantPayConfig;
import com.santiago.core.mapper.MerchantPayConfigMapper;
import com.santiago.core.service.MerchantPayConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class MerchantPayConfigServiceImpl implements MerchantPayConfigService {
    @Autowired
    MerchantPayConfigMapper merchantPayConfigMapper;

//    @Override
//    public MerchantPayConfig getByPayKey(String payKey) {
//        Example example = new Example(MerchantPayConfig.class);
//        example.createCriteria().andEqualTo("payKey", payKey);
//        return merchantPayConfigMapper.selectOneByExample(example);
//    }

    @Override
    public MerchantPayConfig getByMerchantNo(String merchantNo) {
        Example example = new Example(MerchantPayConfig.class);
        example.createCriteria().andEqualTo("merchantNo", merchantNo);
        return merchantPayConfigMapper.selectOneByExample(example);
    }

    @Override
    public void insert(MerchantPayConfig userPayConfig) {
        merchantPayConfigMapper.insert(userPayConfig);
    }

}
