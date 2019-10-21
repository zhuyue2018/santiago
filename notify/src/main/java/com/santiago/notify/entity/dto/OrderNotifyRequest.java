package com.santiago.notify.entity.dto;

public class OrderNotifyRequest {
    String merchantNo;
    String merchantOrderNo;
    String orderStatus;

    public OrderNotifyRequest(String merchantNo, String merchantOrderNo, String orderStatus) {
        this.merchantNo = merchantNo;
        this.merchantOrderNo = merchantOrderNo;
        this.orderStatus = orderStatus;
    }
}
