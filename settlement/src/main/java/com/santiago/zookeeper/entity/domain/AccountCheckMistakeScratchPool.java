package com.santiago.zookeeper.entity.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "account_check_mistake_scratch_pool")
public class AccountCheckMistakeScratchPool {
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

    /**
     * 商品名称
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 商户订单号
     */
    @Column(name = "merchant_order_no")
    private String merchantOrderNo;

    /**
     * 支付流水号
     */
    @Column(name = "trx_no")
    private String trxNo;

    /**
     * 银行订单号
     */
    @Column(name = "bank_order_no")
    private String bankOrderNo;

    /**
     * 银行流水号
     */
    @Column(name = "bank_trx_no")
    private String bankTrxNo;

    /**
     * 订单金额
     */
    @Column(name = "order_amount")
    private BigDecimal orderAmount;

    /**
     * 平台收入
     */
    @Column(name = "plat_income")
    private BigDecimal platIncome;

    /**
     * 费率
     */
    @Column(name = "fee_rate")
    private BigDecimal feeRate;

    /**
     * 平台成本
     */
    @Column(name = "plat_cost")
    private BigDecimal platCost;

    /**
     * 平台利润
     */
    @Column(name = "plat_profit")
    private BigDecimal platProfit;

    /**
     * 状态(参考枚举:paymentrecordstatusenum)
     */
    private String status;

    /**
     * 支付通道编号
     */
    @Column(name = "pay_way_code")
    private String payWayCode;

    /**
     * 支付通道名称
     */
    @Column(name = "pay_way_name")
    private String payWayName;

    /**
     * 支付成功时间
     */
    @Column(name = "pay_success_time")
    private Date paySuccessTime;

    /**
     * 完成时间
     */
    @Column(name = "complete_time")
    private Date completeTime;

    /**
     * 是否退款(100:是,101:否,默认值为:101)
     */
    @Column(name = "is_refund")
    private String isRefund;

    /**
     * 退款次数(默认值为:0)
     */
    @Column(name = "refund_times")
    private Short refundTimes;

    /**
     * 成功退款总金额
     */
    @Column(name = "success_refund_amount")
    private BigDecimal successRefundAmount;

    /**
     * 备注
     */
    private String remark;

    @Column(name = "batch_no")
    private String batchNo;

    @Column(name = "bill_date")
    private Date billDate;

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
     * 获取商品名称
     *
     * @return product_name - 商品名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置商品名称
     *
     * @param productName 商品名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 获取商户订单号
     *
     * @return merchant_order_no - 商户订单号
     */
    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    /**
     * 设置商户订单号
     *
     * @param merchantOrderNo 商户订单号
     */
    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }

    /**
     * 获取支付流水号
     *
     * @return trx_no - 支付流水号
     */
    public String getTrxNo() {
        return trxNo;
    }

    /**
     * 设置支付流水号
     *
     * @param trxNo 支付流水号
     */
    public void setTrxNo(String trxNo) {
        this.trxNo = trxNo;
    }

    /**
     * 获取银行订单号
     *
     * @return bank_order_no - 银行订单号
     */
    public String getBankOrderNo() {
        return bankOrderNo;
    }

    /**
     * 设置银行订单号
     *
     * @param bankOrderNo 银行订单号
     */
    public void setBankOrderNo(String bankOrderNo) {
        this.bankOrderNo = bankOrderNo;
    }

    /**
     * 获取银行流水号
     *
     * @return bank_trx_no - 银行流水号
     */
    public String getBankTrxNo() {
        return bankTrxNo;
    }

    /**
     * 设置银行流水号
     *
     * @param bankTrxNo 银行流水号
     */
    public void setBankTrxNo(String bankTrxNo) {
        this.bankTrxNo = bankTrxNo;
    }

    /**
     * 获取订单金额
     *
     * @return order_amount - 订单金额
     */
    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    /**
     * 设置订单金额
     *
     * @param orderAmount 订单金额
     */
    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    /**
     * 获取平台收入
     *
     * @return plat_income - 平台收入
     */
    public BigDecimal getPlatIncome() {
        return platIncome;
    }

    /**
     * 设置平台收入
     *
     * @param platIncome 平台收入
     */
    public void setPlatIncome(BigDecimal platIncome) {
        this.platIncome = platIncome;
    }

    /**
     * 获取费率
     *
     * @return fee_rate - 费率
     */
    public BigDecimal getFeeRate() {
        return feeRate;
    }

    /**
     * 设置费率
     *
     * @param feeRate 费率
     */
    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }

    /**
     * 获取平台成本
     *
     * @return plat_cost - 平台成本
     */
    public BigDecimal getPlatCost() {
        return platCost;
    }

    /**
     * 设置平台成本
     *
     * @param platCost 平台成本
     */
    public void setPlatCost(BigDecimal platCost) {
        this.platCost = platCost;
    }

    /**
     * 获取平台利润
     *
     * @return plat_profit - 平台利润
     */
    public BigDecimal getPlatProfit() {
        return platProfit;
    }

    /**
     * 设置平台利润
     *
     * @param platProfit 平台利润
     */
    public void setPlatProfit(BigDecimal platProfit) {
        this.platProfit = platProfit;
    }

    /**
     * 获取状态(参考枚举:paymentrecordstatusenum)
     *
     * @return status - 状态(参考枚举:paymentrecordstatusenum)
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态(参考枚举:paymentrecordstatusenum)
     *
     * @param status 状态(参考枚举:paymentrecordstatusenum)
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取支付通道编号
     *
     * @return pay_way_code - 支付通道编号
     */
    public String getPayWayCode() {
        return payWayCode;
    }

    /**
     * 设置支付通道编号
     *
     * @param payWayCode 支付通道编号
     */
    public void setPayWayCode(String payWayCode) {
        this.payWayCode = payWayCode;
    }

    /**
     * 获取支付通道名称
     *
     * @return pay_way_name - 支付通道名称
     */
    public String getPayWayName() {
        return payWayName;
    }

    /**
     * 设置支付通道名称
     *
     * @param payWayName 支付通道名称
     */
    public void setPayWayName(String payWayName) {
        this.payWayName = payWayName;
    }

    /**
     * 获取支付成功时间
     *
     * @return pay_success_time - 支付成功时间
     */
    public Date getPaySuccessTime() {
        return paySuccessTime;
    }

    /**
     * 设置支付成功时间
     *
     * @param paySuccessTime 支付成功时间
     */
    public void setPaySuccessTime(Date paySuccessTime) {
        this.paySuccessTime = paySuccessTime;
    }

    /**
     * 获取完成时间
     *
     * @return complete_time - 完成时间
     */
    public Date getCompleteTime() {
        return completeTime;
    }

    /**
     * 设置完成时间
     *
     * @param completeTime 完成时间
     */
    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    /**
     * 获取是否退款(100:是,101:否,默认值为:101)
     *
     * @return is_refund - 是否退款(100:是,101:否,默认值为:101)
     */
    public String getIsRefund() {
        return isRefund;
    }

    /**
     * 设置是否退款(100:是,101:否,默认值为:101)
     *
     * @param isRefund 是否退款(100:是,101:否,默认值为:101)
     */
    public void setIsRefund(String isRefund) {
        this.isRefund = isRefund;
    }

    /**
     * 获取退款次数(默认值为:0)
     *
     * @return refund_times - 退款次数(默认值为:0)
     */
    public Short getRefundTimes() {
        return refundTimes;
    }

    /**
     * 设置退款次数(默认值为:0)
     *
     * @param refundTimes 退款次数(默认值为:0)
     */
    public void setRefundTimes(Short refundTimes) {
        this.refundTimes = refundTimes;
    }

    /**
     * 获取成功退款总金额
     *
     * @return success_refund_amount - 成功退款总金额
     */
    public BigDecimal getSuccessRefundAmount() {
        return successRefundAmount;
    }

    /**
     * 设置成功退款总金额
     *
     * @param successRefundAmount 成功退款总金额
     */
    public void setSuccessRefundAmount(BigDecimal successRefundAmount) {
        this.successRefundAmount = successRefundAmount;
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
     * @return batch_no
     */
    public String getBatchNo() {
        return batchNo;
    }

    /**
     * @param batchNo
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
}