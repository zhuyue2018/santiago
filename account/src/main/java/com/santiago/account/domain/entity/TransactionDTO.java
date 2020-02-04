package com.santiago.account.domain.entity;

import java.math.BigDecimal;

public class TransactionDTO {
    private String trxType;
    private BigDecimal amount;
    private String rescAccountNo;
    private String destAccountNo;

    public String getTrxType() {
        return trxType;
    }

    public void setTrxType(String trxType) {
        this.trxType = trxType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRescAccountNo() {
        return rescAccountNo;
    }

    public void setRescAccountNo(String rescAccountNo) {
        this.rescAccountNo = rescAccountNo;
    }

    public String getDestAccountNo() {
        return destAccountNo;
    }

    public void setDestAccountNo(String destAccountNo) {
        this.destAccountNo = destAccountNo;
    }
}
