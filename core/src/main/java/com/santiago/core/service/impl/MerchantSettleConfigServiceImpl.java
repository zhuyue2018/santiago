package com.santiago.core.service.impl;

import com.santiago.core.entity.domain.MerchantSettleConfig;
import com.santiago.core.mapper.MerchantSettleConfigMapper;
import com.santiago.core.service.MerchantSettleConfigService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2019-11-03 17:53
 **/
public class MerchantSettleConfigServiceImpl implements MerchantSettleConfigService {
    @Autowired
    MerchantSettleConfigMapper merchantSettleConfigMapper;
    @Override
    public void insert(MerchantSettleConfig settleConfig) {
        merchantSettleConfigMapper.insert(settleConfig);
    }
}
