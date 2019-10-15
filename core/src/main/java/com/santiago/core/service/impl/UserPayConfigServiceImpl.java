package com.santiago.core.service.impl;

import com.zhuyue.pay0929.core.entity.domain.RpUserPayConfig;
import com.zhuyue.pay0929.core.mapper.RpUserPayConfigMapper;
import com.zhuyue.pay0929.core.service.UserPayConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class UserPayConfigServiceImpl implements UserPayConfigService {
    @Autowired
    RpUserPayConfigMapper userPayConfigMapper;

    @Override
    public RpUserPayConfig getByPayKey(String payKey) {
        Example example = new Example(RpUserPayConfig.class);
        example.createCriteria().andEqualTo("payKey", payKey);
        return userPayConfigMapper.selectOneByExample(example);
    }

    @Override
    public void insert(RpUserPayConfig userPayConfig) {
        userPayConfigMapper.insert(userPayConfig);
    }

}
