package com.santiago.core.entity.dto;

import java.util.Map;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2019-10-24 12:55
 **/
public class MerchantInsertDTO {
    private String merchantName;
    private String accountNo;
    private String mobile;
    private String password;
    private String payPassword;
    private boolean autoSettle;
    private String securityRate;
    private String merchantServerIp;
    private Map<String, String> payProductCode;
    private String realName;

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public boolean isAutoSettle() {
        return autoSettle;
    }

    public void setAutoSettle(boolean autoSettle) {
        this.autoSettle = autoSettle;
    }

    public String getSecurityRate() {
        return securityRate;
    }

    public void setSecurityRate(String securityRate) {
        this.securityRate = securityRate;
    }

    public String getMerchantServerIp() {
        return merchantServerIp;
    }

    public void setMerchantServerIp(String merchantServerIp) {
        this.merchantServerIp = merchantServerIp;
    }

    public Map<String, String> getPayProductCode() {
        return payProductCode;
    }

    public void setPayProductCode(Map<String, String> payProductCode) {
        this.payProductCode = payProductCode;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
