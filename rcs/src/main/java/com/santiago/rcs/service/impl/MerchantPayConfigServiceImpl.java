package com.santiago.rcs.service.impl;

import com.santiago.commons.enums.PublicStatusEnum;
import com.santiago.rcs.entity.domain.MerchantPayConfig;
import com.santiago.rcs.mapper.MerchantPayConfigMapper;
import com.santiago.rcs.service.MerchantPayConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service
public class MerchantPayConfigServiceImpl implements MerchantPayConfigService {
    @Autowired
    MerchantPayConfigMapper merchantPayConfigMapper;

//    @Override
//    public MerchantPayConfig getByPayKey(String payKey) {
//        Example example = new Example(MerchantPayConfig.class);
//        example.createCriteria().andEqualTo("payKey", payKey);
//        return merchantPayConfigMapper.selectOneByExample(example);
//    }

    @Override
    public MerchantPayConfig getByMerchantNo(String merchantNo) {
        Example example = new Example(MerchantPayConfig.class);
        example.createCriteria().andEqualTo("merchantNo", merchantNo);
        return merchantPayConfigMapper.selectOneByExample(example);
    }

    @Override
    public void insert(MerchantPayConfig userPayConfig) {
        merchantPayConfigMapper.insert(userPayConfig);
    }

    @Override
    public void createDefault(String merchantNo, String securityRate, String merchantServerIp) {
        MerchantPayConfig payConfig = new MerchantPayConfig();
        payConfig.setGmtCreate(new Date());
        payConfig.setGmtModified(new Date());
        payConfig.setVersion("1.0.0");
        payConfig.setStatus(PublicStatusEnum.ACTIVE.getCode());
        payConfig.setMerchantNo(merchantNo);
        payConfig.setSecurityRating(securityRate);
        payConfig.setMerchantServerIp(merchantServerIp);
        merchantPayConfigMapper.insert(payConfig);
    }

}
