package com.santiago.settlement.service;

import com.santiago.core.entity.domain.MerchantSettleConfig;
import com.santiago.settlement.entity.domain.AccountHistory;

public interface SettleService {
    void settle(MerchantSettleConfig settleConfig, AccountHistory accountHistory);
}
