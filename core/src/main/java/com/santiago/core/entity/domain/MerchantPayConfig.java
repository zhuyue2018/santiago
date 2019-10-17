package com.santiago.core.entity.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "merchant_pay_config")
public class MerchantPayConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gmt_create")
    private Date gmtCreate;

    @Column(name = "gmt_modified")
    private Date gmtModified;

    private String version;

    private String remark;

    private String status;

    @Column(name = "audit_status")
    private String auditStatus;

    @Column(name = "is_auto_sett")
    private String isAutoSett;

    @Column(name = "merchant_no")
    private String merchantNo;

    @Column(name = "risk_day")
    private Integer riskDay;

    @Column(name = "fund_into_type")
    private String fundIntoType;

    /**
     * 安全等级 none：不验证；ip：验证ip；sign：验证sign和ip
     */
    @Column(name = "security_rating")
    private String securityRating;

    /**
     * 商户服务器IP，可配置多个，用|分开
     */
    @Column(name = "merchant_server_ip")
    private String merchantServerIp;

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
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return audit_status
     */
    public String getAuditStatus() {
        return auditStatus;
    }

    /**
     * @param auditStatus
     */
    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * @return is_auto_sett
     */
    public String getIsAutoSett() {
        return isAutoSett;
    }

    /**
     * @param isAutoSett
     */
    public void setIsAutoSett(String isAutoSett) {
        this.isAutoSett = isAutoSett;
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
     * @return risk_day
     */
    public Integer getRiskDay() {
        return riskDay;
    }

    /**
     * @param riskDay
     */
    public void setRiskDay(Integer riskDay) {
        this.riskDay = riskDay;
    }

    /**
     * @return fund_into_type
     */
    public String getFundIntoType() {
        return fundIntoType;
    }

    /**
     * @param fundIntoType
     */
    public void setFundIntoType(String fundIntoType) {
        this.fundIntoType = fundIntoType;
    }

    /**
     * 获取安全等级 none：不验证；ip：验证ip；sign：验证sign和ip
     *
     * @return security_rating - 安全等级 none：不验证；ip：验证ip；sign：验证sign和ip
     */
    public String getSecurityRating() {
        return securityRating;
    }

    /**
     * 设置安全等级 none：不验证；ip：验证ip；sign：验证sign和ip
     *
     * @param securityRating 安全等级 none：不验证；ip：验证ip；sign：验证sign和ip
     */
    public void setSecurityRating(String securityRating) {
        this.securityRating = securityRating;
    }

    /**
     * 获取商户服务器IP，可配置多个，用|分开
     *
     * @return merchant_server_ip - 商户服务器IP，可配置多个，用|分开
     */
    public String getMerchantServerIp() {
        return merchantServerIp;
    }

    /**
     * 设置商户服务器IP，可配置多个，用|分开
     *
     * @param merchantServerIp 商户服务器IP，可配置多个，用|分开
     */
    public void setMerchantServerIp(String merchantServerIp) {
        this.merchantServerIp = merchantServerIp;
    }
}