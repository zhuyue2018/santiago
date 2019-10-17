package com.santiago.core.entity.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "merchant_pay_info")
public class MerchantPayInfo {
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

    @Column(name = "app_id")
    private String appId;

    @Column(name = "app_sectet")
    private String appSectet;

    @Column(name = "merchant_id")
    private String merchantId;

    @Column(name = "app_type")
    private String appType;

    @Column(name = "merchant_no")
    private String merchantNo;

    @Column(name = "merchant_name")
    private String merchantName;

    @Column(name = "partner_key")
    private String partnerKey;

    @Column(name = "offline_app_id")
    private String offlineAppId;

    @Column(name = "rsa_private_key")
    private String rsaPrivateKey;

    @Column(name = "rsa_public_key")
    private String rsaPublicKey;

    @Column(name = "md5_key")
    private String md5Key;

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
     * @return app_id
     */
    public String getAppId() {
        return appId;
    }

    /**
     * @param appId
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * @return app_sectet
     */
    public String getAppSectet() {
        return appSectet;
    }

    /**
     * @param appSectet
     */
    public void setAppSectet(String appSectet) {
        this.appSectet = appSectet;
    }

    /**
     * @return merchant_id
     */
    public String getMerchantId() {
        return merchantId;
    }

    /**
     * @param merchantId
     */
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * @return app_type
     */
    public String getAppType() {
        return appType;
    }

    /**
     * @param appType
     */
    public void setAppType(String appType) {
        this.appType = appType;
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
     * @return merchant_name
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * @param merchantName
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    /**
     * @return partner_key
     */
    public String getPartnerKey() {
        return partnerKey;
    }

    /**
     * @param partnerKey
     */
    public void setPartnerKey(String partnerKey) {
        this.partnerKey = partnerKey;
    }

    /**
     * @return offline_app_id
     */
    public String getOfflineAppId() {
        return offlineAppId;
    }

    /**
     * @param offlineAppId
     */
    public void setOfflineAppId(String offlineAppId) {
        this.offlineAppId = offlineAppId;
    }

    /**
     * @return rsa_private_key
     */
    public String getRsaPrivateKey() {
        return rsaPrivateKey;
    }

    /**
     * @param rsaPrivateKey
     */
    public void setRsaPrivateKey(String rsaPrivateKey) {
        this.rsaPrivateKey = rsaPrivateKey;
    }

    /**
     * @return rsa_public_key
     */
    public String getRsaPublicKey() {
        return rsaPublicKey;
    }

    /**
     * @param rsaPublicKey
     */
    public void setRsaPublicKey(String rsaPublicKey) {
        this.rsaPublicKey = rsaPublicKey;
    }

    /**
     * @return md5_key
     */
    public String getMd5Key() {
        return md5Key;
    }

    /**
     * @param md5Key
     */
    public void setMd5Key(String md5Key) {
        this.md5Key = md5Key;
    }
}