package com.santiago.core.entity.result;

public class DiffRecord {
    private String bankOrderNo;
    private String orderAmount;
    private String status;

    public DiffRecord(String bankOrderNo, String orderAmount, String status) {
        this.bankOrderNo = bankOrderNo;
        this.orderAmount = orderAmount;
        this.status = status;
    }
}
