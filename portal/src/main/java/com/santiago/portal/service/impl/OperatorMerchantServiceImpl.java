package com.santiago.portal.service.impl;

import com.santiago.portal.entity.domain.PmsOperatorMerchant;
import com.santiago.portal.mapper.PmsOperatorMerchantMapper;
import com.santiago.portal.service.OperatorMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2019-10-24 13:32
 **/
@Service
public class OperatorMerchantServiceImpl implements OperatorMerchantService {
    @Autowired
    PmsOperatorMerchantMapper operatorMerchantMapper;
    @Override
    public PmsOperatorMerchant create(Long operatorId, Long merchantId) {
        PmsOperatorMerchant operatorMerchant = new PmsOperatorMerchant();
        operatorMerchant.setOperatorId(operatorId);
        operatorMerchant.setMerchantId(merchantId);
        operatorMerchantMapper.insert(operatorMerchant);
        return operatorMerchant;
    }
}
