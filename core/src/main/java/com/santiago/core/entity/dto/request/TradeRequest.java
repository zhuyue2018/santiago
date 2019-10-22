package com.santiago.core.entity.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TradeRequest {
    @NotNull(message = "merchantNo should not be null")
    private String merchantNo;
    @Size(max = 20, min = 1, message = "productName should be between 1 and 20")
    private String productName;
    @Size(max = 20, min = 1, message = "orderNo should be between 1 and 20")
    private String orderNo;
    @NotNull(message = "orderPriceStr should not be null")
    private String orderPriceStr;
    @NotNull(message = "orderIp should not be null")
    private String orderIp;
    @NotNull(message = "payProductCode should not be null")
    private String payProductCode;
    @NotNull(message = "orderTimeStr should not be null")
    private String orderTimeStr;
    @NotNull(message = "orderPeriodStr should not be null")
    private String orderPeriodStr;
    @NotNull(message = "returnUrl should not be null")
    private String returnUrl;
    @NotNull(message = "notifyUrl should not be null")
    private String notifyUrl;
    @NotNull(message = "remark should not be null")
    private String remark;
    @NotNull(message = "sign should not be null")
    private String sign; // 签名
    @Size(max = 50, message = "field1 should not be longer than 50")
    private String field1;
    @Size(max = 50, message = "field2 should not be longer than 50")
    private String field2;
    @Size(max = 50, message = "field3 should not be longer than 50")
    private String field3;
    @Size(max = 50, message = "field5 should not be longer than 50")
    private String field4;
    @Size(max = 50, message = "field5 should not be longer than 50")
    private String field5;

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderPriceStr() {
        return orderPriceStr;
    }

    public void setOrderPriceStr(String orderPriceStr) {
        this.orderPriceStr = orderPriceStr;
    }

    public String getPayProductCode() {
        return payProductCode;
    }

    public void setPayProductCode(String payProductCode) {
        this.payProductCode = payProductCode;
    }

    public String getOrderIp() {
        return orderIp;
    }

    public void setOrderIp(String orderIp) {
        this.orderIp = orderIp;
    }

    public String getOrderTimeStr() {
        return orderTimeStr;
    }

    public void setOrderTimeStr(String orderTimeStr) {
        this.orderTimeStr = orderTimeStr;
    }

    public String getOrderPeriodStr() {
        return orderPeriodStr;
    }

    public void setOrderPeriodStr(String orderPeriodStr) {
        this.orderPeriodStr = orderPeriodStr;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    public String getField4() {
        return field4;
    }

    public void setField4(String field4) {
        this.field4 = field4;
    }

    public String getField5() {
        return field5;
    }

    public void setField5(String field5) {
        this.field5 = field5;
    }

    @Override
    public String toString() {
        return "TradeRequest{" +
                "merchantNo='" + merchantNo + '\'' +
                ", productName='" + productName + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", orderPriceStr='" + orderPriceStr + '\'' +
                ", orderIp='" + orderIp + '\'' +
                ", payProductCode='" + payProductCode + '\'' +
                ", orderTimeStr='" + orderTimeStr + '\'' +
                ", orderPeriodStr='" + orderPeriodStr + '\'' +
                ", returnUrl='" + returnUrl + '\'' +
                ", notifyUrl='" + notifyUrl + '\'' +
                ", remark='" + remark + '\'' +
                ", sign='" + sign + '\'' +
                ", field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                ", field3='" + field3 + '\'' +
                ", field4='" + field4 + '\'' +
                ", field5='" + field5 + '\'' +
                '}';
    }
}
