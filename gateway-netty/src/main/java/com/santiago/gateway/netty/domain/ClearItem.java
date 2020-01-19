package com.santiago.gateway.netty.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

/**
 * @program: dependency
 * @description:保存清分策略的中间类
 * @author: zhuyue
 * @create: 2019-12-02 19:37
 **/
public class ClearItem {
    private String cusBillNo;
    private String cusName;
    private String cusNo;
    @JsonIgnore
    private String orgCardId;
    @JsonIgnore
    private String clearRate;
    private BigDecimal clearAmount;

    public String getCusBillNo() {
        return cusBillNo;
    }

    public void setCusBillNo(String cusBillNo) {
        this.cusBillNo = cusBillNo;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusNo() {
        return cusNo;
    }

    public void setCusNo(String cusNo) {
        this.cusNo = cusNo;
    }

    public String getOrgCardId() {
        return orgCardId;
    }

    public void setOrgCardId(String orgCardId) {
        this.orgCardId = orgCardId;
    }

    public String getClearRate() {
        return clearRate;
    }

    public void setClearRate(String clearRate) {
        this.clearRate = clearRate;
    }

    public BigDecimal getClearAmount() {
        return clearAmount;
    }

    public void setClearAmount(BigDecimal clearAmount) {
        this.clearAmount = clearAmount;
    }
}
