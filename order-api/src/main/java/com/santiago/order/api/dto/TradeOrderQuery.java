package com.santiago.order.api.dto;


/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2019-10-16 10:07
 **/
public class TradeOrderQuery {
    private String status;
    private String merchantOrderNo;
    private String merchantNo;
    private String payProductCode;
    private String trxNo;
    private Integer pageNum;
    private Integer pageSize;
    private String beginTime;
    private String endTime;

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

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
