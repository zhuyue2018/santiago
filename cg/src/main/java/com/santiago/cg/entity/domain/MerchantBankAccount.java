package com.santiago.cg.entity.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "merchant_bank_account")
public class MerchantBankAccount {
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

    @Column(name = "user_no")
    private String userNo;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "bank_code")
    private String bankCode;

    @Column(name = "bank_account_name")
    private String bankAccountName;

    @Column(name = "bank_account_no")
    private String bankAccountNo;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "card_no")
    private String cardNo;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "is_default")
    private String isDefault;

    private String province;

    private String city;

    private String areas;

    private String street;

    @Column(name = "bank_account_type")
    private String bankAccountType;

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
     * @return bank_name
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * @return bank_code
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     * @param bankCode
     */
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    /**
     * @return bank_account_name
     */
    public String getBankAccountName() {
        return bankAccountName;
    }

    /**
     * @param bankAccountName
     */
    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    /**
     * @return bank_account_no
     */
    public String getBankAccountNo() {
        return bankAccountNo;
    }

    /**
     * @param bankAccountNo
     */
    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    /**
     * @return card_type
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * @param cardType
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    /**
     * @return card_no
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * @param cardNo
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * @return mobile_no
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * @param mobileNo
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    /**
     * @return is_default
     */
    public String getIsDefault() {
        return isDefault;
    }

    /**
     * @param isDefault
     */
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * @return province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return areas
     */
    public String getAreas() {
        return areas;
    }

    /**
     * @param areas
     */
    public void setAreas(String areas) {
        this.areas = areas;
    }

    /**
     * @return street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return bank_account_type
     */
    public String getBankAccountType() {
        return bankAccountType;
    }

    /**
     * @param bankAccountType
     */
    public void setBankAccountType(String bankAccountType) {
        this.bankAccountType = bankAccountType;
    }
}