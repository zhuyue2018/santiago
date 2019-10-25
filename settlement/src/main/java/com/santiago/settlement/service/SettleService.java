package com.santiago.settlement.service;

import com.santiago.settlement.entity.domain.AccountHistory;
import com.santiago.settlement.entity.domain.MerchantSettleConfig;

public interface SettleService {
    void settle(MerchantSettleConfig settleConfig, AccountHistory accountHistory);
}
