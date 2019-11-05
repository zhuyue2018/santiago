package com.santiago.core.wss;

import com.santiago.commons.enums.PublicStatusEnum;
import com.santiago.commons.enums.StatusEnum;
import com.santiago.commons.enums.VersionEnum;
import com.santiago.commons.util.EncryptUtil;
import com.santiago.core.entity.domain.*;
import com.santiago.core.entity.dto.MerchantInsertDTO;
import com.santiago.core.mapper.AccountMapper;
import com.santiago.core.mapper.MerchantInfoMapper;
import com.santiago.core.mapper.MerchantPayConfigMapper;
import com.santiago.core.mapper.MerchantPayInfoMapper;
import com.santiago.core.service.*;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.Date;

@Controller
public class MerchantWss {
    @Autowired
    BuildNoService buildNoService;
    @Autowired
    MerchantInfoService merchantInfoService;
    @Autowired
    MerchantPayInfoService merchantPayInfoService;
    @Autowired
    MerchantPayConfigService merchantPayConfigService;
    @Autowired
    MerchantSettleConfigService merchantSettleConfigService;
    @Autowired
    AccountService accountService;
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
        accountService.createDaefaultAccount(accountNo, merchantNo);
        // 生成支付配置
        merchantPayConfigService.createDefault(merchantNo, dto.getSecurityRate(), dto.getMerchantServerIp());
        // 生成支付信息
        merchantPayInfoService.createDefault(merchantNo, dto.getMerchantName(), "123456");
        // 生成支付产品
        String payProductCode = "001";
        BigDecimal feeRate = new BigDecimal("0");
        merchantPayProductService.create(merchantNo, payProductCode, feeRate);
        //生成对账配置信息
        MerchantSettleConfig settleConfig = new MerchantSettleConfig();
        settleConfig.setGmtCreate(DateTime.now().toDate());
        settleConfig.setGmtModified(DateTime.now().toDate());
        settleConfig.setVersion(VersionEnum.INIT.getCode());
        settleConfig.setCreater("core");
        settleConfig.setMerchantNo(merchantNo);
        settleConfig.setSettleType("1");
        settleConfig.setSettlePeriod(1);
        settleConfig.setIsAutoSettle("0");
        merchantSettleConfigService.insert(settleConfig);
        return merchantInfo.getId();
    }
}
