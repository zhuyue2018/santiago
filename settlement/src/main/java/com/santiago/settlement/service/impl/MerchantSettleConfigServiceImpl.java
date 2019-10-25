package com.santiago.settlement.service.impl;

import com.santiago.settlement.entity.domain.MerchantSettleConfig;
import com.santiago.settlement.mapper.MerchantSettleConfigMapper;
import com.santiago.settlement.service.MerchantSettleConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2019-10-25 13:15
 **/
@Service
public class MerchantSettleConfigServiceImpl implements MerchantSettleConfigService {
    @Autowired
    MerchantSettleConfigMapper configMapper;
    @Override
    public List<MerchantSettleConfig> listAutoSettle() {
        MerchantSettleConfig settleConfig = new MerchantSettleConfig();
        settleConfig.setIsAutoSettle("0");
        return list(settleConfig);
    }

    @Override
    public List<MerchantSettleConfig> list(MerchantSettleConfig merchantSettleConfig) {
        return configMapper.select(merchantSettleConfig);
    }

    @Override
    public MerchantSettleConfig get(MerchantSettleConfig merchantSettleConfig) {
        return configMapper.selectOne(merchantSettleConfig);
    }

    @Override
    public void insert(MerchantSettleConfig settleConfig) {
        configMapper.insert(settleConfig);
    }
}
