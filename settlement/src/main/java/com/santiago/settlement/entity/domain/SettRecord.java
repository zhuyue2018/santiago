package com.santiago.settlement.entity.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sett_record")
public class SettRecord {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 版本号
     */
    private String version;

    /**
     * 创建时间
     */
    @Column(name = "gmt_create")
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @Column(name = "gmt_modified")
    private Date gmtModified;

    /**
     * 结算发起方式(参考settmodetypeenum)
     */
    @Column(name = "sett_mode")
    private String settMode;

    /**
     * 账户编号
     */
    @Column(name = "account_no")
    private String accountNo;

    /**
     * 用户编号
     */
    @Column(name = "merchant_no")
    private String merchantNo;

    /**
     * 用户姓名
     */
    @Column(name = "merchant_name")
    private String merchantName;

    /**
     * 用户类型
     */
    @Column(name = "merchant_type")
    private String merchantType;

    /**
     * 结算日期
     */
    @Column(name = "sett_date")
    private Date settDate;

    /**
     * 银行编码
     */
    @Column(name = "bank_code")
    private String bankCode;

    /**
     * 银行名称
     */
    @Column(name = "bank_name")
    private String bankName;

    /**
     * 开户名
     */
    @Column(name = "bank_account_name")
    private String bankAccountName;

    /**
     * 开户账户
     */
    @Column(name = "bank_account_no")
    private String bankAccountNo;

    /**
     * 开户账户
     */
    @Column(name = "bank_account_type")
    private String bankAccountType;

    /**
     * 开户行所在国家
     */
    private String country;

    /**
     * 开户行所在省份
     */
    private String province;

    /**
     * 开户行所在城市
     */
    private String city;

    /**
     * 开户行所在区
     */
    private String areas;

    /**
     * 开户行全称
     */
    @Column(name = "bank_account_address")
    private String bankAccountAddress;

    /**
     * 收款人手机号
     */
    @Column(name = "mobile_no")
    private String mobileNo;

    /**
     * 结算金额
     */
    @Column(name = "sett_amount")
    private BigDecimal settAmount;

    /**
     * 结算手续费
     */
    @Column(name = "sett_fee")
    private BigDecimal settFee;

    /**
     * 结算打款金额
     */
    @Column(name = "remit_amount")
    private BigDecimal remitAmount;

    /**
     * 结算状态(参考枚举:settrecordstatusenum)
     */
    @Column(name = "sett_status")
    private String settStatus;

    /**
     * 打款确认时间
     */
    @Column(name = "remit_confirm_time")
    private Date remitConfirmTime;

    /**
     * 描述
     */
    private String remark;

    /**
     * 打款备注
     */
    @Column(name = "remit_remark")
    private String remitRemark;

    /**
     * 操作员登录名
     */
    @Column(name = "operator_loginname")
    private String operatorLoginname;

    /**
     * 操作员姓名
     */
    @Column(name = "operator_realname")
    private String operatorRealname;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取版本号
     *
     * @return version - 版本号
     */
    public String getVersion() {
        return version;
    }

    /**
     * 设置版本号
     *
     * @param version 版本号
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 获取创建时间
     *
     * @return gmt_create - 创建时间
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置创建时间
     *
     * @param gmtCreate 创建时间
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取修改时间
     *
     * @return gmt_modified - 修改时间
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置修改时间
     *
     * @param gmtModified 修改时间
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 获取结算发起方式(参考settmodetypeenum)
     *
     * @return sett_mode - 结算发起方式(参考settmodetypeenum)
     */
    public String getSettMode() {
        return settMode;
    }

    /**
     * 设置结算发起方式(参考settmodetypeenum)
     *
     * @param settMode 结算发起方式(参考settmodetypeenum)
     */
    public void setSettMode(String settMode) {
        this.settMode = settMode;
    }

    /**
     * 获取账户编号
     *
     * @return account_no - 账户编号
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * 设置账户编号
     *
     * @param accountNo 账户编号
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    /**
     * 获取用户编号
     *
     * @return merchant_no - 用户编号
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 设置用户编号
     *
     * @param merchantNo 用户编号
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    /**
     * 获取用户姓名
     *
     * @return merchant_name - 用户姓名
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * 设置用户姓名
     *
     * @param merchantName 用户姓名
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    /**
     * 获取用户类型
     *
     * @return merchant_type - 用户类型
     */
    public String getMerchantType() {
        return merchantType;
    }

    /**
     * 设置用户类型
     *
     * @param merchantType 用户类型
     */
    public void setMerchantType(String merchantType) {
        this.merchantType = merchantType;
    }

    /**
     * 获取结算日期
     *
     * @return sett_date - 结算日期
     */
    public Date getSettDate() {
        return settDate;
    }

    /**
     * 设置结算日期
     *
     * @param settDate 结算日期
     */
    public void setSettDate(Date settDate) {
        this.settDate = settDate;
    }

    /**
     * 获取银行编码
     *
     * @return bank_code - 银行编码
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     * 设置银行编码
     *
     * @param bankCode 银行编码
     */
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    /**
     * 获取银行名称
     *
     * @return bank_name - 银行名称
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * 设置银行名称
     *
     * @param bankName 银行名称
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * 获取开户名
     *
     * @return bank_account_name - 开户名
     */
    public String getBankAccountName() {
        return bankAccountName;
    }

    /**
     * 设置开户名
     *
     * @param bankAccountName 开户名
     */
    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    /**
     * 获取开户账户
     *
     * @return bank_account_no - 开户账户
     */
    public String getBankAccountNo() {
        return bankAccountNo;
    }

    /**
     * 设置开户账户
     *
     * @param bankAccountNo 开户账户
     */
    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    /**
     * 获取开户账户
     *
     * @return bank_account_type - 开户账户
     */
    public String getBankAccountType() {
        return bankAccountType;
    }

    /**
     * 设置开户账户
     *
     * @param bankAccountType 开户账户
     */
    public void setBankAccountType(String bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    /**
     * 获取开户行所在国家
     *
     * @return country - 开户行所在国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置开户行所在国家
     *
     * @param country 开户行所在国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取开户行所在省份
     *
     * @return province - 开户行所在省份
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置开户行所在省份
     *
     * @param province 开户行所在省份
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取开户行所在城市
     *
     * @return city - 开户行所在城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置开户行所在城市
     *
     * @param city 开户行所在城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取开户行所在区
     *
     * @return areas - 开户行所在区
     */
    public String getAreas() {
        return areas;
    }

    /**
     * 设置开户行所在区
     *
     * @param areas 开户行所在区
     */
    public void setAreas(String areas) {
        this.areas = areas;
    }

    /**
     * 获取开户行全称
     *
     * @return bank_account_address - 开户行全称
     */
    public String getBankAccountAddress() {
        return bankAccountAddress;
    }

    /**
     * 设置开户行全称
     *
     * @param bankAccountAddress 开户行全称
     */
    public void setBankAccountAddress(String bankAccountAddress) {
        this.bankAccountAddress = bankAccountAddress;
    }

    /**
     * 获取收款人手机号
     *
     * @return mobile_no - 收款人手机号
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * 设置收款人手机号
     *
     * @param mobileNo 收款人手机号
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    /**
     * 获取结算金额
     *
     * @return sett_amount - 结算金额
     */
    public BigDecimal getSettAmount() {
        return settAmount;
    }

    /**
     * 设置结算金额
     *
     * @param settAmount 结算金额
     */
    public void setSettAmount(BigDecimal settAmount) {
        this.settAmount = settAmount;
    }

    /**
     * 获取结算手续费
     *
     * @return sett_fee - 结算手续费
     */
    public BigDecimal getSettFee() {
        return settFee;
    }

    /**
     * 设置结算手续费
     *
     * @param settFee 结算手续费
     */
    public void setSettFee(BigDecimal settFee) {
        this.settFee = settFee;
    }

    /**
     * 获取结算打款金额
     *
     * @return remit_amount - 结算打款金额
     */
    public BigDecimal getRemitAmount() {
        return remitAmount;
    }

    /**
     * 设置结算打款金额
     *
     * @param remitAmount 结算打款金额
     */
    public void setRemitAmount(BigDecimal remitAmount) {
        this.remitAmount = remitAmount;
    }

    /**
     * 获取结算状态(参考枚举:settrecordstatusenum)
     *
     * @return sett_status - 结算状态(参考枚举:settrecordstatusenum)
     */
    public String getSettStatus() {
        return settStatus;
    }

    /**
     * 设置结算状态(参考枚举:settrecordstatusenum)
     *
     * @param settStatus 结算状态(参考枚举:settrecordstatusenum)
     */
    public void setSettStatus(String settStatus) {
        this.settStatus = settStatus;
    }

    /**
     * 获取打款确认时间
     *
     * @return remit_confirm_time - 打款确认时间
     */
    public Date getRemitConfirmTime() {
        return remitConfirmTime;
    }

    /**
     * 设置打款确认时间
     *
     * @param remitConfirmTime 打款确认时间
     */
    public void setRemitConfirmTime(Date remitConfirmTime) {
        this.remitConfirmTime = remitConfirmTime;
    }

    /**
     * 获取描述
     *
     * @return remark - 描述
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置描述
     *
     * @param remark 描述
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取打款备注
     *
     * @return remit_remark - 打款备注
     */
    public String getRemitRemark() {
        return remitRemark;
    }

    /**
     * 设置打款备注
     *
     * @param remitRemark 打款备注
     */
    public void setRemitRemark(String remitRemark) {
        this.remitRemark = remitRemark;
    }

    /**
     * 获取操作员登录名
     *
     * @return operator_loginname - 操作员登录名
     */
    public String getOperatorLoginname() {
        return operatorLoginname;
    }

    /**
     * 设置操作员登录名
     *
     * @param operatorLoginname 操作员登录名
     */
    public void setOperatorLoginname(String operatorLoginname) {
        this.operatorLoginname = operatorLoginname;
    }

    /**
     * 获取操作员姓名
     *
     * @return operator_realname - 操作员姓名
     */
    public String getOperatorRealname() {
        return operatorRealname;
    }

    /**
     * 设置操作员姓名
     *
     * @param operatorRealname 操作员姓名
     */
    public void setOperatorRealname(String operatorRealname) {
        this.operatorRealname = operatorRealname;
    }
}