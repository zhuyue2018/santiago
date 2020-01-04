package com.santiago.core.service.impl;

import com.santiago.commons.enums.PublicStatusEnum;
import com.santiago.core.entity.domain.MerchantPayInfo;
import com.santiago.core.mapper.MerchantPayInfoMapper;
import com.santiago.core.service.MerchantPayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MerchantPayInfoServiceImpl implements MerchantPayInfoService {
    @Autowired
    MerchantPayInfoMapper merchantPayInfoMapper;
    @Override
    public MerchantPayInfo getByMerchantNo(String merchantNo) {
        MerchantPayInfo merchantPayInfoTemp = new MerchantPayInfo();
        merchantPayInfoTemp.setMerchantNo(merchantNo);
        return merchantPayInfoMapper.selectOne(merchantPayInfoTemp);
    }

    @Override
    public void createDefault(String merchantNo, String merchantName, String md5Key) {
        MerchantPayInfo payInfo = new MerchantPayInfo();
        payInfo.setGmtCreate(new Date());
        payInfo.setGmtModified(new Date());
        payInfo.setVersion("1.0.0");
        payInfo.setStatus(PublicStatusEnum.ACTIVE.getCode());
        payInfo.setMerchantNo(merchantNo);
        payInfo.setMerchantName(merchantName);
        payInfo.setMd5Key(md5Key);
        merchantPayInfoMapper.insert(payInfo);
    }

}
