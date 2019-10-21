package com.santiago.core.entity.dto.response;

import com.santiago.commons.dto.resp.SimpleResponse;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PreOrderResponse extends SimpleResponse {
    private String merchantOrderNo;
    private String trxNo;
    private String returnMsg;
    private String merchantNo;
    private String codeUrl;
    private String codeImageUrl;

    public PreOrderResponse(String code, String msg) {
        super(code, msg);
    }

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }

    public String getTrxNo() {
        return trxNo;
    }

    public void setTrxNo(String trxNo) {
        this.trxNo = trxNo;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public String getCodeImageUrl() {
        return codeImageUrl;
    }

    public void setCodeImageUrl(String codeImageUrl) {
        this.codeImageUrl = codeImageUrl;
    }
}

