package com.santiago.zookeeper.entity.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "account_history")
public class AccountHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gmt_create")
    private Date gmtCreate;

    @Column(name = "gmt_modified")
    private Date gmtModified;

    private String version;

    private String remark;

    @Column(name = "rec_date")
    private String recDate;

    @Column(name = "account_no")
    private String accountNo;

    private BigDecimal amount;

    private BigDecimal balance;

    @Column(name = "fund_direction")
    private String fundDirection;

    /**
     * 0:允许 1:不允许
     */
    @Column(name = "is_allow_sett")
    private String isAllowSett;

    @Column(name = "sett_status")
    private String settStatus;

    @Column(name = "request_no")
    private String requestNo;

    @Column(name = "bank_trx_no")
    private String bankTrxNo;

    @Column(name = "trx_type")
    private String trxType;

    @Column(name = "risk_day")
    private Integer riskDay;

    @Column(name = "merchant_no")
    private String merchantNo;

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
     * @return rec_date
     */
    public String getRecDate() {
        return recDate;
    }

    /**
     * @param recDate
     */
    public void setRecDate(String recDate) {
        this.recDate = recDate;
    }

    /**
     * @return account_no
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * @param accountNo
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    /**
     * @return amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * @param balance
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * @return fund_direction
     */
    public String getFundDirection() {
        return fundDirection;
    }

    /**
     * @param fundDirection
     */
    public void setFundDirection(String fundDirection) {
        this.fundDirection = fundDirection;
    }

    /**
     * 获取0:允许 1:不允许
     *
     * @return is_allow_sett - 0:允许 1:不允许
     */
    public String getIsAllowSett() {
        return isAllowSett;
    }

    /**
     * 设置0:允许 1:不允许
     *
     * @param isAllowSett 0:允许 1:不允许
     */
    public void setIsAllowSett(String isAllowSett) {
        this.isAllowSett = isAllowSett;
    }

    /**
     * @return sett_status
     */
    public String getSettStatus() {
        return settStatus;
    }

    /**
     * @param settStatus
     */
    public void setSettStatus(String settStatus) {
        this.settStatus = settStatus;
    }

    /**
     * @return request_no
     */
    public String getRequestNo() {
        return requestNo;
    }

    /**
     * @param requestNo
     */
    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    /**
     * @return bank_trx_no
     */
    public String getBankTrxNo() {
        return bankTrxNo;
    }

    /**
     * @param bankTrxNo
     */
    public void setBankTrxNo(String bankTrxNo) {
        this.bankTrxNo = bankTrxNo;
    }

    /**
     * @return trx_type
     */
    public String getTrxType() {
        return trxType;
    }

    /**
     * @param trxType
     */
    public void setTrxType(String trxType) {
        this.trxType = trxType;
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
}