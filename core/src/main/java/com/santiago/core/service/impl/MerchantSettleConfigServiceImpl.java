package com.santiago.core.service.impl;

import com.santiago.core.entity.domain.MerchantSettleConfig;
import com.santiago.core.mapper.MerchantSettleConfigMapper;
import com.santiago.core.service.MerchantSettleConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2019-11-03 17:53
 **/
@Service
public class MerchantSettleConfigServiceImpl implements MerchantSettleConfigService {
    @Autowired
    MerchantSettleConfigMapper merchantSettleConfigMapper;
    @Override
    public void insert(MerchantSettleConfig settleConfig) {
        merchantSettleConfigMapper.insert(settleConfig);
    }
    @Override
    public List<MerchantSettleConfig> listAutoSettle() {
        MerchantSettleConfig settleConfig = new MerchantSettleConfig();
        settleConfig.setIsAutoSettle("0");
        return list(settleConfig);
    }

    @Override
    public List<MerchantSettleConfig> list(MerchantSettleConfig merchantSettleConfig) {
        return merchantSettleConfigMapper.select(merchantSettleConfig);
    }

    @Override
    public MerchantSettleConfig get(MerchantSettleConfig merchantSettleConfig) {
        return merchantSettleConfigMapper.selectOne(merchantSettleConfig);
    }

}
