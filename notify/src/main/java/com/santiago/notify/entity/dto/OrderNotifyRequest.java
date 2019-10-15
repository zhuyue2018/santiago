package com.santiago.notify.entity.dto;

public class OrderNotifyRequest {
    String url;
    String merchantNo;
    String merchantOrderNo;
    String orderStatus;

    public OrderNotifyRequest(String url, String merchantNo, String merchantOrderNo, String orderStatus) {
        this.url = url;
        this.merchantNo = merchantNo;
        this.merchantOrderNo = merchantOrderNo;
        this.orderStatus = orderStatus;
    }
}
