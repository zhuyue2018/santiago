package com.santiago.portal.entity.dto.request;

import java.util.HashMap;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2019-10-24 12:15
 **/
public class MerchantInsertReq {
    private String insertMerchantName;
    private String insertAccountNo;
    private String insertMobile;
    private String insertPassword;
    private String insertPayPassword;
    private boolean insertAutoSettle;
    private String insertSecurityRate;
    private String insertMerchantServerIp;
    private HashMap<String, String> insertPayProductCode;
    private String insertRealName;

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
