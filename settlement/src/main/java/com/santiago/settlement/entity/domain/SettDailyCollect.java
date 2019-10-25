package com.santiago.settlement.entity.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sett_daily_collect")
public class SettDailyCollect {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 版本号
     */
    private Integer version;

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
     * 账户编号
     */
    @Column(name = "account_no")
    private String accountNo;

    /**
     * 用户姓名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 汇总日期
     */
    @Column(name = "collect_date")
    private Date collectDate;

    /**
     * 汇总类型(参考枚举:settdailycollecttypeenum)
     */
    @Column(name = "collect_type")
    private String collectType;

    /**
     * 交易总金额
     */
    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    /**
     * 交易总笔数
     */
    @Column(name = "total_count")
    private Integer totalCount;

    /**
     * 结算状态(参考枚举:settdailycollectstatusenum)
     */
    @Column(name = "sett_status")
    private String settStatus;

    /**
     * 备注
     */
    private String remark;

    /**
     * 风险预存期天数
     */
    @Column(name = "risk_day")
    private Integer riskDay;

    /**
     * 获取id
     *
     * @return id - id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取版本号
     *
     * @return version - 版本号
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 设置版本号
     *
     * @param version 版本号
     */
    public void setVersion(Integer version) {
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
     * 获取用户姓名
     *
     * @return user_name - 用户姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户姓名
     *
     * @param userName 用户姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取汇总日期
     *
     * @return collect_date - 汇总日期
     */
    public Date getCollectDate() {
        return collectDate;
    }

    /**
     * 设置汇总日期
     *
     * @param collectDate 汇总日期
     */
    public void setCollectDate(Date collectDate) {
        this.collectDate = collectDate;
    }

    /**
     * 获取汇总类型(参考枚举:settdailycollecttypeenum)
     *
     * @return collect_type - 汇总类型(参考枚举:settdailycollecttypeenum)
     */
    public String getCollectType() {
        return collectType;
    }

    /**
     * 设置汇总类型(参考枚举:settdailycollecttypeenum)
     *
     * @param collectType 汇总类型(参考枚举:settdailycollecttypeenum)
     */
    public void setCollectType(String collectType) {
        this.collectType = collectType;
    }

    /**
     * 获取交易总金额
     *
     * @return total_amount - 交易总金额
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * 设置交易总金额
     *
     * @param totalAmount 交易总金额
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 获取交易总笔数
     *
     * @return total_count - 交易总笔数
     */
    public Integer getTotalCount() {
        return totalCount;
    }

    /**
     * 设置交易总笔数
     *
     * @param totalCount 交易总笔数
     */
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 获取结算状态(参考枚举:settdailycollectstatusenum)
     *
     * @return sett_status - 结算状态(参考枚举:settdailycollectstatusenum)
     */
    public String getSettStatus() {
        return settStatus;
    }

    /**
     * 设置结算状态(参考枚举:settdailycollectstatusenum)
     *
     * @param settStatus 结算状态(参考枚举:settdailycollectstatusenum)
     */
    public void setSettStatus(String settStatus) {
        this.settStatus = settStatus;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取风险预存期天数
     *
     * @return risk_day - 风险预存期天数
     */
    public Integer getRiskDay() {
        return riskDay;
    }

    /**
     * 设置风险预存期天数
     *
     * @param riskDay 风险预存期天数
     */
    public void setRiskDay(Integer riskDay) {
        this.riskDay = riskDay;
    }
}