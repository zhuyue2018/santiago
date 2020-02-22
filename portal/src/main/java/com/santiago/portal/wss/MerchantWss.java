package com.santiago.portal.wss;

import com.santiago.commons.service.BuildNoService;
import com.santiago.commons.util.EncryptUtil;
import com.santiago.portal.entity.domain.MerchantInfo;
import com.santiago.portal.entity.dto.MerchantInsertDTO;
import com.santiago.portal.service.MerchantInfoService;
import com.santiago.portal.service.MerchantPayInfoService;
import com.santiago.portal.service.MerchantPayProductService;
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
//    @Autowired
//    AccountApi accountApi;
    @Autowired
    MerchantPayProductService merchantPayProductService;


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
//        accountApi.create(accountNo, merchantNo);
        // 生成支付配置
//        rcsApi.createMerchantPayConfig(merchantNo, dto.getSecurityRate(), dto.getMerchantServerIp());
        // 生成支付信息
        merchantPayInfoService.createDefault(merchantNo, dto.getMerchantName(), "123456");
        // 生成支付产品
        String payProductCode = "001";
        BigDecimal feeRate = new BigDecimal("0");
        merchantPayProductService.create(merchantNo, payProductCode, feeRate);
        //生成对账配置信息
//        rcsApi.createMerchantSettleConfig(merchantNo);
        return merchantInfo.getId();
    }

}
