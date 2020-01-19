package com.santiago.remit.dto;

import java.math.BigDecimal;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2020-01-19 16:54
 **/
public class Order {
    private String serNo;
    private BigDecimal amount;
    private String payerNo;
    private String payeeNo;

    public String getSerNo() {
        return serNo;
    }

    public void setSerNo(String serNo) {
        this.serNo = serNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPayerNo() {
        return payerNo;
    }

    public void setPayerNo(String payerNo) {
        this.payerNo = payerNo;
    }

    public String getPayeeNo() {
        return payeeNo;
    }

    public void setPayeeNo(String payeeNo) {
        this.payeeNo = payeeNo;
    }
}
