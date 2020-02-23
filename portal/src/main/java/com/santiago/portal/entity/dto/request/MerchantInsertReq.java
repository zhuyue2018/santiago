package com.santiago.portal.entity.dto.request;

import javax.validation.constraints.NotNull;
import java.util.HashMap;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2019-10-24 12:15
 **/
public class MerchantInsertReq {
    @NotNull
    private String insertMerchantName;
    @NotNull
    private String insertAccountNo;
    @NotNull
    private String insertMobile;
    @NotNull
    private String insertPassword;
    @NotNull
    private String insertPayPassword;
    @NotNull
    private boolean insertAutoSettle;
    @NotNull
    private String insertSecurityRate;
    @NotNull
    private String insertMerchantServerIp;
    @NotNull
    private HashMap<String, String> insertPayProductCode;
    @NotNull
    private String insertRealName;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getInsertMerchantName() {
        return insertMerchantName;
    }

    public void setInsertMerchantName(String insertMerchantName) {
        this.insertMerchantName = insertMerchantName;
    }

    public String getInsertAccountNo() {
        return insertAccountNo;
    }

    public void setInsertAccountNo(String insertAccountNo) {
        this.insertAccountNo = insertAccountNo;
    }

    public String getInsertMobile() {
        return insertMobile;
    }

    public void setInsertMobile(String insertMobile) {
        this.insertMobile = insertMobile;
    }

    public String getInsertPassword() {
        return insertPassword;
    }

    public void setInsertPassword(String insertPassword) {
        this.insertPassword = insertPassword;
    }

    public String getInsertPayPassword() {
        return insertPayPassword;
    }

    public void setInsertPayPassword(String insertPayPassword) {
        this.insertPayPassword = insertPayPassword;
    }

    public boolean isInsertAutoSettle() {
        return insertAutoSettle;
    }

    public void setInsertAutoSettle(boolean insertAutoSettle) {
        this.insertAutoSettle = insertAutoSettle;
    }

    public String getInsertSecurityRate() {
        return insertSecurityRate;
    }

    public void setInsertSecurityRate(String insertSecurityRate) {
        this.insertSecurityRate = insertSecurityRate;
    }

    public String getInsertMerchantServerIp() {
        return insertMerchantServerIp;
    }

    public void setInsertMerchantServerIp(String insertMerchantServerIp) {
        this.insertMerchantServerIp = insertMerchantServerIp;
    }

    public HashMap<String, String> getInsertPayProductCode() {
        return insertPayProductCode;
    }

    public void setInsertPayProductCode(HashMap<String, String> insertPayProductCode) {
        this.insertPayProductCode = insertPayProductCode;
    }

    public String getInsertRealName() {
        return insertRealName;
    }

    public void setInsertRealName(String insertRealName) {
        this.insertRealName = insertRealName;
    }
}
