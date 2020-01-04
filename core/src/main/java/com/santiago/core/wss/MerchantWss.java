package com.santiago.core.wss;

import com.santiago.api.AccountApi;
import com.santiago.api.RcsApi;
import com.santiago.commons.util.EncryptUtil;
import com.santiago.core.entity.domain.*;
import com.santiago.core.entity.dto.MerchantInsertDTO;
import com.santiago.core.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

@Controller
public class MerchantWss {
    @Autowired
    BuildNoService buildNoService;
    @Autowired
    MerchantInfoService merchantInfoService;
    @Autowired
    MerchantPayInfoService merchantPayInfoService;
    @Autowired
    RcsApi merchantPayConfigApi;
    @Autowired
    AccountApi accountApi;
    @Autowired
    MerchantPayProductService merchantPayProductService;
    @Autowired
    RcsApi rcsApi;

    public Long register(MerchantInsertDTO dto) {
        String merchantNo = buildNoService.buildUserNo();
        String accountNo = buildNoService.buildAccountNo();
        //生成用户信息
        MerchantInfo merchantInfo = new MerchantInfo();
        merchantInfo.setMerchantNo(merchantNo);
        merchantInfo.setMerchantName(dto.getMerchantName());
        merchantInfo.setAccountNo(accountNo);
        merchantInfo.setMobile(dto.getMobile());
        merchantInfo.setPassword(EncryptUtil.encodeMD5String(dto.getPassword()));
        merchantInfo.setPayPwd(EncryptUtil.encodeMD5String(dto.getPayPassword()));
        merchantInfoService.createDefault(merchantInfo);
        // 生成账户信息
        accountApi.create(accountNo, merchantNo);
        // 生成支付配置
        rcsApi.createMerchantPayConfig(merchantNo, dto.getSecurityRate(), dto.getMerchantServerIp());
        // 生成支付信息
        merchantPayInfoService.createDefault(merchantNo, dto.getMerchantName(), "123456");
        // 生成支付产品
        String payProductCode = "001";
        BigDecimal feeRate = new BigDecimal("0");
        merchantPayProductService.create(merchantNo, payProductCode, feeRate);
        //生成对账配置信息
        rcsApi.createMerchantSettleConfig(merchantNo);
        return merchantInfo.getId();
    }

}
