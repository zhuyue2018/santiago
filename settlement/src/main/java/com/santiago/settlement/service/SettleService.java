package com.santiago.settlement.service;

import com.santiago.api.dto.MerchantSettleConfig;
import com.santiago.settlement.entity.domain.AccountHistory;

public interface SettleService {
    void settle(MerchantSettleConfig settleConfig, AccountHistory accountHistory);
}
