package com.santiago.core.wss;

import com.zhuyue.pay0929.commons.util.EncryptUtil;
import com.zhuyue.pay0929.commons.enums.PublicStatusEnum;
import com.zhuyue.pay0929.core.entity.domain.RpAccount;
import com.zhuyue.pay0929.core.entity.domain.RpUserInfo;
import com.zhuyue.pay0929.core.mapper.RpAccountMapper;
import com.zhuyue.pay0929.core.mapper.RpUserInfoMapper;
import com.zhuyue.pay0929.core.service.BuildNoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class MerchantWss {
    @Autowired
    BuildNoService buildNoService;
    @Autowired
    RpUserInfoMapper userInfoMapper;
    @Autowired
    RpAccountMapper accountMapper;

    public void register(String merchantName, String mobile, String password) {
        String merchantNo = buildNoService.buildUserNo();
        String accountNo = buildNoService.buildAccountNo();
        //生成用户信息
        RpUserInfo rpUserInfo = new RpUserInfo();
        rpUserInfo.setAccountNo(accountNo);
        rpUserInfo.setGmtCreate(new Date());
//        rpUserInfo.setId(UUID.randomUUID().toString());
        rpUserInfo.setStatus(PublicStatusEnum.ACTIVE.name());
        rpUserInfo.setMerchantName(merchantName);
        rpUserInfo.setMerchantNo(merchantNo);
        rpUserInfo.setMobile(mobile);
        rpUserInfo.setPassword(EncryptUtil.encodeMD5String(password));
        rpUserInfo.setPayPwd(EncryptUtil.encodeMD5String("123456"));
        userInfoMapper.insert(rpUserInfo);
        // 生成账户信息
        RpAccount rpAccount = new RpAccount();
        rpAccount.setAccountNo(accountNo);// todo
        rpAccount.setAccountType("0");
        rpAccount.setGmtCreate(new Date());
        rpAccount.setStatus(PublicStatusEnum.ACTIVE.name());
        rpAccount.setMerchantNo(merchantNo);
        rpAccount.setBalance(new BigDecimal("0"));
        rpAccount.setSecurityMoney(new BigDecimal("0"));
        rpAccount.setSettAmount(new BigDecimal("0"));
        rpAccount.setTodayExpend(new BigDecimal("0"));
        rpAccount.setTodayIncome(new BigDecimal("0"));
        rpAccount.setFreezeBalance(new BigDecimal("0"));
        rpAccount.setTotalExpend(new BigDecimal("0"));
        rpAccount.setTotalIncome(new BigDecimal("0"));
        accountMapper.insert(rpAccount);
        // 生成操作员信息 todo

    }
}
