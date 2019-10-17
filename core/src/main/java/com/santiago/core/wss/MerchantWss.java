package com.santiago.core.wss;

import com.santiago.commons.enums.PublicStatusEnum;
import com.santiago.commons.util.EncryptUtil;
import com.santiago.core.entity.domain.Account;
import com.santiago.core.entity.domain.MerchantInfo;
import com.santiago.core.mapper.AccountMapper;
import com.santiago.core.mapper.MerchantInfoMapper;
import com.santiago.core.service.BuildNoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class MerchantWss {
    @Autowired
    BuildNoService buildNoService;
    @Autowired
    MerchantInfoMapper merchantInfoMapper;
    @Autowired
    AccountMapper accountMapper;

    public void register(String merchantName, String mobile, String password) {
        String merchantNo = buildNoService.buildUserNo();
        String accountNo = buildNoService.buildAccountNo();
        //生成用户信息
        MerchantInfo merchantInfo = new MerchantInfo();
        merchantInfo.setAccountNo(accountNo);
        merchantInfo.setGmtCreate(new Date());
//        merchantInfo.setId(UUID.randomUUID().toString());
        merchantInfo.setStatus(PublicStatusEnum.ACTIVE.name());
        merchantInfo.setMerchantName(merchantName);
        merchantInfo.setMerchantNo(merchantNo);
        merchantInfo.setMobile(mobile);
        merchantInfo.setPassword(EncryptUtil.encodeMD5String(password));
        merchantInfo.setPayPwd(EncryptUtil.encodeMD5String("123456"));
        merchantInfoMapper.insert(merchantInfo);
        // 生成账户信息
        Account account = new Account();
        account.setAccountNo(accountNo);// todo
        account.setAccountType("0");
        account.setGmtCreate(new Date());
        account.setStatus(PublicStatusEnum.ACTIVE.name());
        account.setMerchantNo(merchantNo);
        account.setBalance(new BigDecimal("0"));
        account.setSecurityMoney(new BigDecimal("0"));
        account.setSettAmount(new BigDecimal("0"));
        account.setTodayExpend(new BigDecimal("0"));
        account.setTodayIncome(new BigDecimal("0"));
        account.setFreezeBalance(new BigDecimal("0"));
        account.setTotalExpend(new BigDecimal("0"));
        account.setTotalIncome(new BigDecimal("0"));
        accountMapper.insert(account);
        // 生成操作员信息 todo

    }
}
