package com.santiago.zookeeper.service;

import com.santiago.core.entity.domain.MerchantSettleConfig;
import com.santiago.zookeeper.entity.domain.AccountHistory;

public interface SettleService {
    void settle(MerchantSettleConfig settleConfig, AccountHistory accountHistory);
}
