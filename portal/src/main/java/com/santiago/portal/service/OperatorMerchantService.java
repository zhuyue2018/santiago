package com.santiago.portal.service;

import com.santiago.portal.entity.domain.PmsOperatorMerchant;

public interface OperatorMerchantService {
    PmsOperatorMerchant create(Long operatorId, Long merchantId);
}
