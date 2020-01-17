package com.santiago.settlement.entity.domain;

import javax.persistence.*;
import java.util.Date;

@Table(name = "merchant_settle_config")
public class MerchantSettleConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gmt_create")
    private Date gmtCreate;

    @Column(name = "gmt_modified")
    private Date gmtModified;

    private String version;

    private String creater;

    private String editor;

    @Column(name = "merchant_no")
    private String merchantNo;

    @Column(name = "settle_type")
    private String settleType;

    @Column(name = "settle_period")
    private Integer settlePeriod;

    @Column(name = "is_auto_settle")
    private String isAutoSettle;

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
     * @return creater
     */
    public String getCreater() {
        return creater;
    }

    /**
     * @param creater
     */
    public void setCreater(String creater) {
        this.creater = creater;
    }

    /**
     * @return editor
     */
    public String getEditor() {
        return editor;
    }

    /**
     * @param editor
     */
    public void setEditor(String editor) {
        this.editor = editor;
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
     * @return settle_type
     */
    public String getSettleType() {
        return settleType;
    }

    /**
     * @param settleType
     */
    public void setSettleType(String settleType) {
        this.settleType = settleType;
    }

    /**
     * @return settle_period
     */
    public Integer getSettlePeriod() {
        return settlePeriod;
    }

    /**
     * @param settlePeriod
     */
    public void setSettlePeriod(Integer settlePeriod) {
        this.settlePeriod = settlePeriod;
    }

    /**
     * @return is_auto_settle
     */
    public String getIsAutoSettle() {
        return isAutoSettle;
    }

    /**
     * @param isAutoSettle
     */
    public void setIsAutoSettle(String isAutoSettle) {
        this.isAutoSettle = isAutoSettle;
    }
}