package com.santiago.core.entity.domain;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "merchant_pay_product")
public class MerchantPayProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "merchant_no")
    private String merchantNo;

    @Column(name = "pay_product_code")
    private String payProductCode;

    @Column(name = "fee_rate")
    private BigDecimal feeRate;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return merchant_no
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * @param merchantNo
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    /**
     * @return pay_product_code
     */
    public String getPayProductCode() {
        return payProductCode;
    }

    /**
     * @param payProductCode
     */
    public void setPayProductCode(String payProductCode) {
        this.payProductCode = payProductCode;
    }

    /**
     * @return fee_rate
     */
    public BigDecimal getFeeRate() {
        return feeRate;
    }

    /**
     * @param feeRate
     */
    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }
}