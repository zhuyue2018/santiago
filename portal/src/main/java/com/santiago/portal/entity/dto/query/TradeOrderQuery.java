package com.santiago.portal.entity.dto.query;

import com.santiago.commons.dto.query.BaseQuery;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2019-10-16 10:07
 **/
public class TradeOrderQuery extends BaseQuery {
    private String status;
    private String merchantOrderNo;
    private String merchantNo;
    private String payProductCode;
    private String trxNo;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getPayProductCode() {
        return payProductCode;
    }

    public void setPayProductCode(String payProductCode) {
        this.payProductCode = payProductCode;
    }

    public String getTrxNo() {
        return trxNo;
    }

    public void setTrxNo(String trxNo) {
        this.trxNo = trxNo;
    }
}
