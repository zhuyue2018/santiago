package com.santiago.core.entity.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "rp_user_pay_config")
public class RpUserPayConfig {
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

    /**
     * 支付产品编号
     */
    @Column(name = "product_code")
    private String productCode;

    /**
     * 支付产品名称
     */
    @Column(name = "product_name")
    private String productName;

    @Column(name = "user_no")
    private String userNo;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "risk_day")
    private Integer riskDay;

    @Column(name = "pay_key")
    private String payKey;

    @Column(name = "fund_into_type")
    private String fundIntoType;

    @Column(name = "pay_secret")
    private String paySecret;

    /**
     * 安全等级
     */
    @Column(name = "security_rating")
    private String securityRating;

    /**
     * 商户服务器IP
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
     * 获取支付产品编号
     *
     * @return product_code - 支付产品编号
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * 设置支付产品编号
     *
     * @param productCode 支付产品编号
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * 获取支付产品名称
     *
     * @return product_name - 支付产品名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置支付产品名称
     *
     * @param productName 支付产品名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return user_no
     */
    public String getUserNo() {
        return userNo;
    }

    /**
     * @param userNo
     */
    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    /**
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
     * @return pay_key
     */
    public String getPayKey() {
        return payKey;
    }

    /**
     * @param payKey
     */
    public void setPayKey(String payKey) {
        this.payKey = payKey;
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
     * @return pay_secret
     */
    public String getPaySecret() {
        return paySecret;
    }

    /**
     * @param paySecret
     */
    public void setPaySecret(String paySecret) {
        this.paySecret = paySecret;
    }

    /**
     * 获取安全等级
     *
     * @return security_rating - 安全等级
     */
    public String getSecurityRating() {
        return securityRating;
    }

    /**
     * 设置安全等级
     *
     * @param securityRating 安全等级
     */
    public void setSecurityRating(String securityRating) {
        this.securityRating = securityRating;
    }

    /**
     * 获取商户服务器IP
     *
     * @return merchant_server_ip - 商户服务器IP
     */
    public String getMerchantServerIp() {
        return merchantServerIp;
    }

    /**
     * 设置商户服务器IP
     *
     * @param merchantServerIp 商户服务器IP
     */
    public void setMerchantServerIp(String merchantServerIp) {
        this.merchantServerIp = merchantServerIp;
    }
}