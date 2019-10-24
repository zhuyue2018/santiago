package com.santiago.core.wss;

import com.santiago.commons.enums.PublicStatusEnum;
import com.santiago.commons.util.EncryptUtil;
import com.santiago.core.entity.domain.Account;
import com.santiago.core.entity.domain.MerchantInfo;
import com.santiago.core.entity.domain.MerchantPayConfig;
import com.santiago.core.entity.domain.MerchantPayInfo;
import com.santiago.core.entity.dto.MerchantInsertDTO;
import com.santiago.core.mapper.AccountMapper;
import com.santiago.core.mapper.MerchantInfoMapper;
import com.santiago.core.mapper.MerchantPayConfigMapper;
import com.santiago.core.mapper.MerchantPayInfoMapper;
import com.santiago.core.service.BuildNoService;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sun.security.provider.MD5;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Controller
public class MerchantWss {
    @Autowired
    BuildNoService buildNoService;
    @Autowired
    MerchantInfoMapper merchantInfoMapper;
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    MerchantPayConfigMapper payConfigMapper;
    @Autowired
    MerchantPayInfoMapper payInfoMapper;

    public Long register(MerchantInsertDTO dto) {
        String merchantNo = buildNoService.buildUserNo();
        String accountNo = buildNoService.buildAccountNo();
        //生成用户信息
        MerchantInfo merchantInfo = new MerchantInfo();
        merchantInfo.setGmtCreate(new Date());
        merchantInfo.setGmtModified(new Date());
        merchantInfo.setStatus(PublicStatusEnum.ACTIVE.name());
        merchantInfo.setMerchantNo(merchantNo);
        merchantInfo.setMerchantName(dto.getMerchantName());
        merchantInfo.setAccountNo(accountNo);
        merchantInfo.setMobile(dto.getMobile());
        merchantInfo.setPassword(EncryptUtil.encodeMD5String(dto.getPassword()));
        merchantInfo.setPayPwd(EncryptUtil.encodeMD5String(dto.getPayPassword()));
        merchantInfoMapper.insert(merchantInfo);
        // 生成账户信息
        Account account = new Account();
        account.setGmtCreate(new Date());
        account.setGmtModified(new Date());
        account.setVersion("1.0.0");
        account.setAccountNo(accountNo);// todo
        account.setBalance(new BigDecimal("0"));
        account.setFreezeBalance(new BigDecimal("0"));
        account.setSecurityMoney(new BigDecimal("0"));
        account.setStatus(PublicStatusEnum.ACTIVE.name());
        account.setTotalExpend(new BigDecimal("0"));
        account.setTotalIncome(new BigDecimal("0"));
        account.setTodayIncome(new BigDecimal("0"));
        account.setTodayExpend(new BigDecimal("0"));
        account.setAccountType("0");
        account.setSettAmount(new BigDecimal("0"));
        account.setMerchantNo(merchantNo);
        accountMapper.insert(account);
        // 生成支付配置
        MerchantPayConfig payConfig = new MerchantPayConfig();
        payConfig.setGmtCreate(new Date());
        payConfig.setGmtModified(new Date());
        payConfig.setVersion("1.0.0");
        payConfig.setStatus(PublicStatusEnum.ACTIVE.getCode());
        payConfig.setMerchantNo(merchantNo);
        payConfig.setSecurityRating(dto.getSecurityRate());
        payConfig.setMerchantServerIp(dto.getMerchantServerIp());
        payConfigMapper.insert(payConfig);
        // 生成支付信息
        MerchantPayInfo payInfo = new MerchantPayInfo();
        payInfo.setGmtCreate(new Date());
        payInfo.setGmtModified(new Date());
        payInfo.setVersion("1.0.0");
        payInfo.setStatus(PublicStatusEnum.ACTIVE.getCode());
        payInfo.setMerchantNo(merchantNo);
        payInfo.setMerchantName(dto.getMerchantName());
        payInfo.setMd5Key("todo");
        payInfoMapper.insert(payInfo);
        return merchantInfo.getId();
    }
}
