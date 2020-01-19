package com.santiago.settlement.service;

import com.santiago.api.dto.MerchantSettleConfig;

import java.util.List;

public interface MerchantSettleConfigService {
    List<MerchantSettleConfig> listAutoSettle();
    List<MerchantSettleConfig> list(MerchantSettleConfig merchantSettleConfig);
    MerchantSettleConfig get(MerchantSettleConfig merchantSettleConfig);
    void insert(MerchantSettleConfig settleConfig);
}
