package com.santiago.rcs.entity.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "clear_book")
public class ClearBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "order_no")
    private String orderNo;

    private BigDecimal amount;

    @Column(name = "parent_order_no")
    private String parentOrderNo;

    @Column(name = "parent_amount")
    private String parentAmount;

    @Column(name = "primary_cus_no")
    private String primaryCusNo;

    @Column(name = "primary_cus_name")
    private String primaryCusName;

    @Column(name = "secondary_cus_no")
    private String secondaryCusNo;

    @Column(name = "secondary_cus_name")
    private String secondaryCusName;

    @Column(name = "trx_type")
    private String trxType;

    @Column(name = "pay_serial_no")
    private String paySerialNo;

    @Column(name = "order_create_time")
    private Date orderCreateTime;

    @Column(name = "order_succ_time")
    private Date orderSuccTime;

    @Column(name = "need_separate")
    private String needSeparate;

    @Column(name = "fee_type")
    private String feeType;

    @Column(name = "account_time")
    private Date accountTime;

    @Column(name = "account_serial_no")
    private String accountSerialNo;

    /**
     * 该笔订单是否完成清分的状态，包括0-未收款，1-待清分、2-已清分、3-手工清分、C-关闭；
     */
    @Column(name = "clear_state")
    private String clearState;

    @Column(name = "clear_time")
    private Date clearTime;

    @Column(name = "separate_info")
    private String separateInfo;

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
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return order_no
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * @param orderNo
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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
     * @return parent_order_no
     */
    public String getParentOrderNo() {
        return parentOrderNo;
    }

    /**
     * @param parentOrderNo
     */
    public void setParentOrderNo(String parentOrderNo) {
        this.parentOrderNo = parentOrderNo;
    }

    /**
     * @return parent_amount
     */
    public String getParentAmount() {
        return parentAmount;
    }

    /**
     * @param parentAmount
     */
    public void setParentAmount(String parentAmount) {
        this.parentAmount = parentAmount;
    }

    /**
     * @return primary_cus_no
     */
    public String getPrimaryCusNo() {
        return primaryCusNo;
    }

    /**
     * @param primaryCusNo
     */
    public void setPrimaryCusNo(String primaryCusNo) {
        this.primaryCusNo = primaryCusNo;
    }

    /**
     * @return primary_cus_name
     */
    public String getPrimaryCusName() {
        return primaryCusName;
    }

    /**
     * @param primaryCusName
     */
    public void setPrimaryCusName(String primaryCusName) {
        this.primaryCusName = primaryCusName;
    }

    /**
     * @return secondary_cus_no
     */
    public String getSecondaryCusNo() {
        return secondaryCusNo;
    }

    /**
     * @param secondaryCusNo
     */
    public void setSecondaryCusNo(String secondaryCusNo) {
        this.secondaryCusNo = secondaryCusNo;
    }

    /**
     * @return secondary_cus_name
     */
    public String getSecondaryCusName() {
        return secondaryCusName;
    }

    /**
     * @param secondaryCusName
     */
    public void setSecondaryCusName(String secondaryCusName) {
        this.secondaryCusName = secondaryCusName;
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
     * @return pay_serial_no
     */
    public String getPaySerialNo() {
        return paySerialNo;
    }

    /**
     * @param paySerialNo
     */
    public void setPaySerialNo(String paySerialNo) {
        this.paySerialNo = paySerialNo;
    }

    /**
     * @return order_create_time
     */
    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    /**
     * @param orderCreateTime
     */
    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    /**
     * @return order_succ_time
     */
    public Date getOrderSuccTime() {
        return orderSuccTime;
    }

    /**
     * @param orderSuccTime
     */
    public void setOrderSuccTime(Date orderSuccTime) {
        this.orderSuccTime = orderSuccTime;
    }

    /**
     * @return need_separate
     */
    public String getNeedSeparate() {
        return needSeparate;
    }

    /**
     * @param needSeparate
     */
    public void setNeedSeparate(String needSeparate) {
        this.needSeparate = needSeparate;
    }

    /**
     * @return fee_type
     */
    public String getFeeType() {
        return feeType;
    }

    /**
     * @param feeType
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    /**
     * @return account_time
     */
    public Date getAccountTime() {
        return accountTime;
    }

    /**
     * @param accountTime
     */
    public void setAccountTime(Date accountTime) {
        this.accountTime = accountTime;
    }

    /**
     * @return account_serial_no
     */
    public String getAccountSerialNo() {
        return accountSerialNo;
    }

    /**
     * @param accountSerialNo
     */
    public void setAccountSerialNo(String accountSerialNo) {
        this.accountSerialNo = accountSerialNo;
    }

    /**
     * 获取该笔订单是否完成清分的状态，包括0-未收款，1-待清分、2-已清分、3-手工清分、C-关闭；
     *
     * @return clear_state - 该笔订单是否完成清分的状态，包括0-未收款，1-待清分、2-已清分、3-手工清分、C-关闭；
     */
    public String getClearState() {
        return clearState;
    }

    /**
     * 设置该笔订单是否完成清分的状态，包括0-未收款，1-待清分、2-已清分、3-手工清分、C-关闭；
     *
     * @param clearState 该笔订单是否完成清分的状态，包括0-未收款，1-待清分、2-已清分、3-手工清分、C-关闭；
     */
    public void setClearState(String clearState) {
        this.clearState = clearState;
    }

    /**
     * @return clear_time
     */
    public Date getClearTime() {
        return clearTime;
    }

    /**
     * @param clearTime
     */
    public void setClearTime(Date clearTime) {
        this.clearTime = clearTime;
    }

    /**
     * @return separate_info
     */
    public String getSeparateInfo() {
        return separateInfo;
    }

    /**
     * @param separateInfo
     */
    public void setSeparateInfo(String separateInfo) {
        this.separateInfo = separateInfo;
    }
}