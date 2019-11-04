package com.santiago.core.service.impl;

import com.santiago.core.entity.domain.MerchantPayProduct;
import com.santiago.core.mapper.MerchantPayProductMapper;
import com.santiago.core.service.MerchantPayProductService;
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
        merchantPayProduct.setMerchantNo(merchantNo);
        merchantPayProduct.setPayProductCode(payProductCode);
        merchantPayProduct.setFeeRate(feeRate);
        merchantPayProductMapper.insert(merchantPayProduct);
    }
}
