package com.santiago.settlement.entity.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创建时间
     */
    @Column(name = "gmt_create")
    private Date gmtCreate;

    /**
     * 最后修改时间
     */
    @Column(name = "gmt_modified")
    private Date gmtModified;

    /**
     * 版本：为“1.0.0”格式
     */
    private String version;

    /**
     * 说明
     */
    private String remark;

    /**
     * 账户号
     */
    @Column(name = "account_no")
    private String accountNo;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 冻结金额
     */
    @Column(name = "freeze_balance")
    private BigDecimal freezeBalance;

    /**
     * 保证金
     */
    @Column(name = "security_money")
    private BigDecimal securityMoney;

    /**
     * 账户状态：0：未激活，1：正常， 2：冻结， 3：关闭
     */
    private String status;

    /**
     * 总收入
     */
    @Column(name = "total_income")
    private BigDecimal totalIncome;

    /**
     * 总支出
     */
    @Column(name = "total_expend")
    private BigDecimal totalExpend;

    /**
     * 今日收入
     */
    @Column(name = "today_income")
    private BigDecimal todayIncome;

    /**
     * 今日支出
     */
    @Column(name = "today_expend")
    private BigDecimal todayExpend;

    /**
     * 账户类型
     */
    @Column(name = "account_type")
    private String accountType;

    /**
     * 可结算金额
     */
    @Column(name = "unsett_balance")
    private BigDecimal unsettBalance;

    /**
     * 商户号
     */
    @Column(name = "merchant_no")
    private String merchantNo;

    /**
     * 逻辑删除：0为未删除，1为删除
     */
    private String delete;

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
     * 获取最后修改时间
     *
     * @return gmt_modified - 最后修改时间
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置最后修改时间
     *
     * @param gmtModified 最后修改时间
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 获取版本：为“1.0.0”格式
     *
     * @return version - 版本：为“1.0.0”格式
     */
    public String getVersion() {
        return version;
    }

    /**
     * 设置版本：为“1.0.0”格式
     *
     * @param version 版本：为“1.0.0”格式
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 获取说明
     *
     * @return remark - 说明
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置说明
     *
     * @param remark 说明
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取账户号
     *
     * @return account_no - 账户号
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * 设置账户号
     *
     * @param accountNo 账户号
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    /**
     * 获取余额
     *
     * @return balance - 余额
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * 设置余额
     *
     * @param balance 余额
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * 获取冻结金额
     *
     * @return freeze_balance - 冻结金额
     */
    public BigDecimal getFreezeBalance() {
        return freezeBalance;
    }

    /**
     * 设置冻结金额
     *
     * @param freezeBalance 冻结金额
     */
    public void setFreezeBalance(BigDecimal freezeBalance) {
        this.freezeBalance = freezeBalance;
    }

    /**
     * 获取保证金
     *
     * @return security_money - 保证金
     */
    public BigDecimal getSecurityMoney() {
        return securityMoney;
    }

    /**
     * 设置保证金
     *
     * @param securityMoney 保证金
     */
    public void setSecurityMoney(BigDecimal securityMoney) {
        this.securityMoney = securityMoney;
    }

    /**
     * 获取账户状态：0：未激活，1：正常， 2：冻结， 3：关闭
     *
     * @return status - 账户状态：0：未激活，1：正常， 2：冻结， 3：关闭
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置账户状态：0：未激活，1：正常， 2：冻结， 3：关闭
     *
     * @param status 账户状态：0：未激活，1：正常， 2：冻结， 3：关闭
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取总收入
     *
     * @return total_income - 总收入
     */
    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    /**
     * 设置总收入
     *
     * @param totalIncome 总收入
     */
    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    /**
     * 获取总支出
     *
     * @return total_expend - 总支出
     */
    public BigDecimal getTotalExpend() {
        return totalExpend;
    }

    /**
     * 设置总支出
     *
     * @param totalExpend 总支出
     */
    public void setTotalExpend(BigDecimal totalExpend) {
        this.totalExpend = totalExpend;
    }

    /**
     * 获取今日收入
     *
     * @return today_income - 今日收入
     */
    public BigDecimal getTodayIncome() {
        return todayIncome;
    }

    /**
     * 设置今日收入
     *
     * @param todayIncome 今日收入
     */
    public void setTodayIncome(BigDecimal todayIncome) {
        this.todayIncome = todayIncome;
    }

    /**
     * 获取今日支出
     *
     * @return today_expend - 今日支出
     */
    public BigDecimal getTodayExpend() {
        return todayExpend;
    }

    /**
     * 设置今日支出
     *
     * @param todayExpend 今日支出
     */
    public void setTodayExpend(BigDecimal todayExpend) {
        this.todayExpend = todayExpend;
    }

    /**
     * 获取账户类型
     *
     * @return account_type - 账户类型
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * 设置账户类型
     *
     * @param accountType 账户类型
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * 获取可结算金额
     *
     * @return unsett_balance - 可结算金额
     */
    public BigDecimal getUnsettBalance() {
        return unsettBalance;
    }

    /**
     * 设置可结算金额
     *
     * @param unsettBalance 可结算金额
     */
    public void setUnsettBalance(BigDecimal unsettBalance) {
        this.unsettBalance = unsettBalance;
    }

    /**
     * 获取商户号
     *
     * @return merchant_no - 商户号
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 设置商户号
     *
     * @param merchantNo 商户号
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    /**
     * 获取逻辑删除：0为未删除，1为删除
     *
     * @return delete - 逻辑删除：0为未删除，1为删除
     */
    public String getDelete() {
        return delete;
    }

    /**
     * 设置逻辑删除：0为未删除，1为删除
     *
     * @param delete 逻辑删除：0为未删除，1为删除
     */
    public void setDelete(String delete) {
        this.delete = delete;
    }
}