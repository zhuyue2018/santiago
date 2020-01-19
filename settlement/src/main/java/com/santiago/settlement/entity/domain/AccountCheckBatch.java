package com.santiago.settlement.entity.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "account_check_batch")
public class AccountCheckBatch {
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
     * 修改者
     */
    private String editor;

    /**
     * 创建者
     */
    private String creater;

    /**
     * 对账状态：0：未对账，1：已发送， 2：成功， 3：失败
     */
    private String status;

    private String remark;

    /**
     * 对账日期，对的是哪一天的账
     */
    @Column(name = "batch_no")
    private String batchNo;

    @Column(name = "bill_date")
    private Date billDate;

    @Column(name = "bill_type")
    private String billType;

    @Column(name = "handle_status")
    private String handleStatus;

    /**
     * 对应recon_interface的code
     */
    @Column(name = "bank_type")
    private String bankType;

    @Column(name = "mistake_count")
    private Integer mistakeCount;

    @Column(name = "unhandle_mistake_count")
    private Integer unhandleMistakeCount;

    @Column(name = "trade_count")
    private Integer tradeCount;

    @Column(name = "bank_trade_count")
    private Integer bankTradeCount;

    @Column(name = "trade_amount")
    private BigDecimal tradeAmount;

    @Column(name = "bank_trade_amount")
    private BigDecimal bankTradeAmount;

    @Column(name = "refund_amount")
    private BigDecimal refundAmount;

    @Column(name = "bank_refund_amount")
    private BigDecimal bankRefundAmount;

    @Column(name = "bank_fee")
    private BigDecimal bankFee;

    @Column(name = "org_check_file_path")
    private String orgCheckFilePath;

    @Column(name = "release_check_file_path")
    private String releaseCheckFilePath;

    @Column(name = "release_status")
    private String releaseStatus;

    @Column(name = "check_fail_msg")
    private String checkFailMsg;

    @Column(name = "bank_err_msg")
    private String bankErrMsg;

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
     * 获取对账状态：0：未对账，1：已发送， 2：成功， 3：失败
     *
     * @return status - 对账状态：0：未对账，1：已发送， 2：成功， 3：失败
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置对账状态：0：未对账，1：已发送， 2：成功， 3：失败
     *
     * @param status 对账状态：0：未对账，1：已发送， 2：成功， 3：失败
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
     * 获取对账日期，对的是哪一天的账
     *
     * @return batch_no - 对账日期，对的是哪一天的账
     */
    public String getBatchNo() {
        return batchNo;
    }

    /**
     * 设置对账日期，对的是哪一天的账
     *
     * @param batchNo 对账日期，对的是哪一天的账
     */
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
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
     * @return bill_type
     */
    public String getBillType() {
        return billType;
    }

    /**
     * @param billType
     */
    public void setBillType(String billType) {
        this.billType = billType;
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
     * 获取对应recon_interface的code
     *
     * @return bank_type - 对应recon_interface的code
     */
    public String getBankType() {
        return bankType;
    }

    /**
     * 设置对应recon_interface的code
     *
     * @param bankType 对应recon_interface的code
     */
    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    /**
     * @return mistake_count
     */
    public Integer getMistakeCount() {
        return mistakeCount;
    }

    /**
     * @param mistakeCount
     */
    public void setMistakeCount(Integer mistakeCount) {
        this.mistakeCount = mistakeCount;
    }

    /**
     * @return unhandle_mistake_count
     */
    public Integer getUnhandleMistakeCount() {
        return unhandleMistakeCount;
    }

    /**
     * @param unhandleMistakeCount
     */
    public void setUnhandleMistakeCount(Integer unhandleMistakeCount) {
        this.unhandleMistakeCount = unhandleMistakeCount;
    }

    /**
     * @return trade_count
     */
    public Integer getTradeCount() {
        return tradeCount;
    }

    /**
     * @param tradeCount
     */
    public void setTradeCount(Integer tradeCount) {
        this.tradeCount = tradeCount;
    }

    /**
     * @return bank_trade_count
     */
    public Integer getBankTradeCount() {
        return bankTradeCount;
    }

    /**
     * @param bankTradeCount
     */
    public void setBankTradeCount(Integer bankTradeCount) {
        this.bankTradeCount = bankTradeCount;
    }

    /**
     * @return trade_amount
     */
    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    /**
     * @param tradeAmount
     */
    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    /**
     * @return bank_trade_amount
     */
    public BigDecimal getBankTradeAmount() {
        return bankTradeAmount;
    }

    /**
     * @param bankTradeAmount
     */
    public void setBankTradeAmount(BigDecimal bankTradeAmount) {
        this.bankTradeAmount = bankTradeAmount;
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
     * @return org_check_file_path
     */
    public String getOrgCheckFilePath() {
        return orgCheckFilePath;
    }

    /**
     * @param orgCheckFilePath
     */
    public void setOrgCheckFilePath(String orgCheckFilePath) {
        this.orgCheckFilePath = orgCheckFilePath;
    }

    /**
     * @return release_check_file_path
     */
    public String getReleaseCheckFilePath() {
        return releaseCheckFilePath;
    }

    /**
     * @param releaseCheckFilePath
     */
    public void setReleaseCheckFilePath(String releaseCheckFilePath) {
        this.releaseCheckFilePath = releaseCheckFilePath;
    }

    /**
     * @return release_status
     */
    public String getReleaseStatus() {
        return releaseStatus;
    }

    /**
     * @param releaseStatus
     */
    public void setReleaseStatus(String releaseStatus) {
        this.releaseStatus = releaseStatus;
    }

    /**
     * @return check_fail_msg
     */
    public String getCheckFailMsg() {
        return checkFailMsg;
    }

    /**
     * @param checkFailMsg
     */
    public void setCheckFailMsg(String checkFailMsg) {
        this.checkFailMsg = checkFailMsg;
    }

    /**
     * @return bank_err_msg
     */
    public String getBankErrMsg() {
        return bankErrMsg;
    }

    /**
     * @param bankErrMsg
     */
    public void setBankErrMsg(String bankErrMsg) {
        this.bankErrMsg = bankErrMsg;
    }
}