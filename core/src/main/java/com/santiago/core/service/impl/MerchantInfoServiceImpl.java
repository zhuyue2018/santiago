package com.santiago.core.service.impl;

import com.santiago.commons.enums.PublicStatusEnum;
import com.santiago.core.entity.domain.MerchantInfo;
import com.santiago.core.mapper.MerchantInfoMapper;
import com.santiago.core.service.MerchantInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MerchantInfoServiceImpl implements MerchantInfoService {
    @Autowired
    MerchantInfoMapper merchantInfoMapper;
    @Override
    public void createDefault(MerchantInfo merchantInfo) {
        merchantInfo.setGmtCreate(new Date());
        merchantInfo.setGmtModified(new Date());
        merchantInfo.setStatus(PublicStatusEnum.ACTIVE.getCode());
        merchantInfoMapper.insert(merchantInfo);
    }


    @Override
    public List<MerchantInfo> list(MerchantInfo merchantInfo) {
        return merchantInfoMapper.select(merchantInfo);
    }
}
