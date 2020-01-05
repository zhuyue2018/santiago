package com.santiago.job.entity.domain;


public class TradeRequest {
    private String merchantNo;
    private String productName;
    private String orderNo;
    private String orderPriceStr;
    private String orderIp;
    private String payProductCode;
    private String orderTimeStr;
    private String orderPeriodStr;
    private String returnUrl;
    private String notifyUrl;
    private String remark;
    private String sign; // 签名
    private String field1;
    private String field2;
    private String field3;
    private String field4;
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
