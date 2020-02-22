package com.santiago.portal.entity.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "merchant_pay_product")
public class MerchantPayProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gmt_create")
    private Date gmtCreate;

    @Column(name = "gmt_modified")
    private Date gmtModified;

    private String version;

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
     * @return gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * @param gmtCreate
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * @return gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * @param gmtModified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * @return version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version
     */
    public void setVersion(String version) {
        this.version = version;
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