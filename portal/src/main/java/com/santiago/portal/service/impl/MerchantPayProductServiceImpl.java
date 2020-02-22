package com.santiago.portal.service.impl;

import com.santiago.commons.enums.VersionEnum;
import com.santiago.portal.entity.domain.MerchantPayProduct;
import com.santiago.portal.mapper.MerchantPayProductMapper;
import com.santiago.portal.service.MerchantPayProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class MerchantPayProductServiceImpl implements MerchantPayProductService {
    @Autowired
    MerchantPayProductMapper merchantPayProductMapper;
    @Override
    public void create(String merchantNo, String payProductCode, BigDecimal feeRate) {
        MerchantPayProduct merchantPayProduct = new MerchantPayProduct();
        merchantPayProduct.setVersion(VersionEnum.INIT.getCode());
        merchantPayProduct.setMerchantNo(merchantNo);
        merchantPayProduct.setPayProductCode(payProductCode);
        merchantPayProduct.setFeeRate(feeRate);
        merchantPayProductMapper.insert(merchantPayProduct);
    }
}
