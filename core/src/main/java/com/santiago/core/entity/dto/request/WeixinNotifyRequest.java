package com.santiago.core.entity.dto.request;

import javax.validation.constraints.NotNull;

public class WeixinNotifyRequest {
    @NotNull
    private String bankOrderNo;
    @NotNull
    private String status;

    public String getBankOrderNo() {
        return bankOrderNo;
    }

    public void setBankOrderNo(String bankOrderNo) {
        this.bankOrderNo = bankOrderNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
