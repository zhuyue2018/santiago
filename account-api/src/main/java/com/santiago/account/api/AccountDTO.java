package com.santiago.account.api;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

public class AccountDTO {
    private Long id;
    private Date gmtCreate;
    private Date gmtModified;
    private String version;
    private String remark;
    private String accountNo;
    private BigDecimal balance;
    private BigDecimal freezeBalance;
    private BigDecimal securityMoney;
    private String status;
    private BigDecimal totalIncome;
    private BigDecimal totalExpend;
    private BigDecimal todayIncome;
    private BigDecimal todayExpend;
    private String accountType;
    private BigDecimal unsettBalance;
    private String merchantNo;
    private String deleteFlag;
    public Long getId() {
        return id;
    }
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
     * @return delete_flag - 逻辑删除：0为未删除，1为删除
     */
    public String getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * 设置逻辑删除：0为未删除，1为删除
     *
     * @param deleteFlag 逻辑删除：0为未删除，1为删除
     */
    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}