package com.santiago.core.service;

import com.zhuyue.pay0929.core.entity.domain.RpUserPayConfig;

public interface UserPayConfigService {
    RpUserPayConfig getByPayKey(String payKey);

    void insert(RpUserPayConfig userPayConfig);
}
