package com.santiago.zookeeper.entity.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "account_check_mistake")
public class AccountCheckMistake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gmt_create")
    private Date gmtCreate;

    /**
     * 最后修改时间
     */
    @Column(name = "gmt_modified")
    private Date gmtModified;

    private String version;

    /**
     * 创建者
     */
    private String creater;

    /**
     * 修改者
     */
    private String editor;

    private String status;

    private String remark;

    @Column(name = "account_check_batch_no")
    private String accountCheckBatchNo;

    @Column(name = "bill_date")
    private Date billDate;

    @Column(name = "bank_type")
    private String bankType;

    @Column(name = "order_time")
    private Date orderTime;

    @Column(name = "merchant_name")
    private String merchantName;

    @Column(name = "merchant_no")
    private String merchantNo;

    @Column(name = "order_no")
    private String orderNo;

    @Column(name = "trade_time")
    private Date tradeTime;

    @Column(name = "trx_no")
    private String trxNo;

    @Column(name = "order_amount")
    private BigDecimal orderAmount;

    @Column(name = "refund_amount")
    private BigDecimal refundAmount;

    @Column(name = "trade_status")
    private String tradeStatus;

    private BigDecimal fee;

    @Column(name = "bank_trade_time")
    private Date bankTradeTime;

    @Column(name = "bank_order_no")
    private String bankOrderNo;

    @Column(name = "bank_trx_no")
    private String bankTrxNo;

    @Column(name = "bank_trade_status")
    private String bankTradeStatus;

    @Column(name = "bank_amount")
    private BigDecimal bankAmount;

    @Column(name = "bank_refund_amount")
    private BigDecimal bankRefundAmount;

    @Column(name = "bank_fee")
    private BigDecimal bankFee;

    @Column(name = "err_type")
    private String errType;

    @Column(name = "handle_status")
    private String handleStatus;

    @Column(name = "handle_value")
    private String handleValue;

    @Column(name = "handle_remark")
    private String handleRemark;

    @Column(name = "operator_name")
    private String operatorName;

    @Column(name = "operator_account_no")
    private String operatorAccountNo;

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
     * 获取创建者
     *
     * @return creater - 创建者
     */
    public String getCreater() {
        return creater;
    }

    /**
     * 设置创建者
     *
     * @param creater 创建者
     */
    public void setCreater(String creater) {
        this.creater = creater;
    }

    /**
     * 获取修改者
     *
     * @return editor - 修改者
     */
    public String getEditor() {
        return editor;
    }

    /**
     * 设置修改者
     *
     * @param editor 修改者
     */
    public void setEditor(String editor) {
        this.editor = editor;
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
     * @return account_check_batch_no
     */
    public String getAccountCheckBatchNo() {
        return accountCheckBatchNo;
    }

    /**
     * @param accountCheckBatchNo
     */
    public void setAccountCheckBatchNo(String accountCheckBatchNo) {
        this.accountCheckBatchNo = accountCheckBatchNo;
    }

    /**
     * @return bill_date
     */
    public Date getBillDate() {
        return billDate;
    }

    /**
     * @param billDate
     */
    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    /**
     * @return bank_type
     */
    public String getBankType() {
        return bankType;
    }

    /**
     * @param bankType
     */
    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    /**
     * @return order_time
     */
    public Date getOrderTime() {
        return orderTime;
    }

    /**
     * @param orderTime
     */
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
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
     * @return trade_time
     */
    public Date getTradeTime() {
        return tradeTime;
    }

    /**
     * @param tradeTime
     */
    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    /**
     * @return trx_no
     */
    public String getTrxNo() {
        return trxNo;
    }

    /**
     * @param trxNo
     */
    public void setTrxNo(String trxNo) {
        this.trxNo = trxNo;
    }

    /**
     * @return order_amount
     */
    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    /**
     * @param orderAmount
     */
    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    /**
     * @return refund_amount
     */
    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    /**
     * @param refundAmount
     */
    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    /**
     * @return trade_status
     */
    public String getTradeStatus() {
        return tradeStatus;
    }

    /**
     * @param tradeStatus
     */
    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    /**
     * @return fee
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * @param fee
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    /**
     * @return bank_trade_time
     */
    public Date getBankTradeTime() {
        return bankTradeTime;
    }

    /**
     * @param bankTradeTime
     */
    public void setBankTradeTime(Date bankTradeTime) {
        this.bankTradeTime = bankTradeTime;
    }

    /**
     * @return bank_order_no
     */
    public String getBankOrderNo() {
        return bankOrderNo;
    }

    /**
     * @param bankOrderNo
     */
    public void setBankOrderNo(String bankOrderNo) {
        this.bankOrderNo = bankOrderNo;
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
     * @return bank_trade_status
     */
    public String getBankTradeStatus() {
        return bankTradeStatus;
    }

    /**
     * @param bankTradeStatus
     */
    public void setBankTradeStatus(String bankTradeStatus) {
        this.bankTradeStatus = bankTradeStatus;
    }

    /**
     * @return bank_amount
     */
    public BigDecimal getBankAmount() {
        return bankAmount;
    }

    /**
     * @param bankAmount
     */
    public void setBankAmount(BigDecimal bankAmount) {
        this.bankAmount = bankAmount;
    }

    /**
     * @return bank_refund_amount
     */
    public BigDecimal getBankRefundAmount() {
        return bankRefundAmount;
    }

    /**
     * @param bankRefundAmount
     */
    public void setBankRefundAmount(BigDecimal bankRefundAmount) {
        this.bankRefundAmount = bankRefundAmount;
    }

    /**
     * @return bank_fee
     */
    public BigDecimal getBankFee() {
        return bankFee;
    }

    /**
     * @param bankFee
     */
    public void setBankFee(BigDecimal bankFee) {
        this.bankFee = bankFee;
    }

    /**
     * @return err_type
     */
    public String getErrType() {
        return errType;
    }

    /**
     * @param errType
     */
    public void setErrType(String errType) {
        this.errType = errType;
    }

    /**
     * @return handle_status
     */
    public String getHandleStatus() {
        return handleStatus;
    }

    /**
     * @param handleStatus
     */
    public void setHandleStatus(String handleStatus) {
        this.handleStatus = handleStatus;
    }

    /**
     * @return handle_value
     */
    public String getHandleValue() {
        return handleValue;
    }

    /**
     * @param handleValue
     */
    public void setHandleValue(String handleValue) {
        this.handleValue = handleValue;
    }

    /**
     * @return handle_remark
     */
    public String getHandleRemark() {
        return handleRemark;
    }

    /**
     * @param handleRemark
     */
    public void setHandleRemark(String handleRemark) {
        this.handleRemark = handleRemark;
    }

    /**
     * @return operator_name
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * @param operatorName
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    /**
     * @return operator_account_no
     */
    public String getOperatorAccountNo() {
        return operatorAccountNo;
    }

    /**
     * @param operatorAccountNo
     */
    public void setOperatorAccountNo(String operatorAccountNo) {
        this.operatorAccountNo = operatorAccountNo;
    }
}