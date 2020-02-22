package com.santiago.cg.entity.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "refund_record")
public class RefundRecord {
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
     * 修改者
     */
    private String editor;

    /**
     * 创建者
     */
    private String creater;

    /**
     * 最后修改时间
     */
    @Column(name = "gmt_modified")
    private Date gmtModified;

    /**
     * 原商户订单号
     */
    @Column(name = "org_merchant_order_no")
    private String orgMerchantOrderNo;

    /**
     * 原支付流水号
     */
    @Column(name = "org_trx_no")
    private String orgTrxNo;

    /**
     * 原银行订单号
     */
    @Column(name = "org_bank_order_no")
    private String orgBankOrderNo;

    /**
     * 原银行流水号
     */
    @Column(name = "org_bank_trx_no")
    private String orgBankTrxNo;

    /**
     * 商家名称
     */
    @Column(name = "merchant_name")
    private String merchantName;

    /**
     * 商家编号
     */
    @Column(name = "merchant_no")
    private String merchantNo;

    /**
     * 原商品名称
     */
    @Column(name = "org_product_name")
    private String orgProductName;

    /**
     * 原业务类型
     */
    @Column(name = "org_biz_type")
    private String orgBizType;

    /**
     * 原支付方式类型
     */
    @Column(name = "org_payment_type")
    private String orgPaymentType;

    /**
     * 订单退款金额
     */
    @Column(name = "refund_amount")
    private BigDecimal refundAmount;

    /**
     * 退款流水号
     */
    @Column(name = "refund_trx_no")
    private String refundTrxNo;

    /**
     * 退款订单号
     */
    @Column(name = "refund_order_no")
    private String refundOrderNo;

    /**
     * 银行退款订单号
     */
    @Column(name = "bank_refund_order_no")
    private String bankRefundOrderNo;

    /**
     * 银行退款流水号
     */
    @Column(name = "bank_refund_trx_no")
    private String bankRefundTrxNo;

    /**
     * 退款结果通知url
     */
    @Column(name = "result_notify_url")
    private String resultNotifyUrl;

    /**
     * 退款状态
     */
    @Column(name = "refund_status")
    private String refundStatus;

    /**
     * 退款来源（分发平台）
     */
    @Column(name = "refund_from")
    private String refundFrom;

    /**
     * 退款方式
     */
    @Column(name = "refund_way")
    private String refundWay;

    /**
     * 退款请求时间
     */
    @Column(name = "refund_request_time")
    private Date refundRequestTime;

    /**
     *  退款成功时间
     */
    @Column(name = "refund_success_time")
    private Date refundSuccessTime;

    /**
     * 退款完成时间
     */
    @Column(name = "refund_complete_time")
    private Date refundCompleteTime;

    /**
     * 退款请求,申请人登录名
     */
    @Column(name = "request_apply_user_id")
    private String requestApplyUserId;

    /**
     * 退款请求,申请人姓名
     */
    @Column(name = "request_apply_user_name")
    private String requestApplyUserName;

    /**
     * 退款原因
     */
    @Column(name = "refund_reason")
    private String refundReason;

    /**
     * 备注
     */
    private String remark;

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
     * 获取原商户订单号
     *
     * @return org_merchant_order_no - 原商户订单号
     */
    public String getOrgMerchantOrderNo() {
        return orgMerchantOrderNo;
    }

    /**
     * 设置原商户订单号
     *
     * @param orgMerchantOrderNo 原商户订单号
     */
    public void setOrgMerchantOrderNo(String orgMerchantOrderNo) {
        this.orgMerchantOrderNo = orgMerchantOrderNo;
    }

    /**
     * 获取原支付流水号
     *
     * @return org_trx_no - 原支付流水号
     */
    public String getOrgTrxNo() {
        return orgTrxNo;
    }

    /**
     * 设置原支付流水号
     *
     * @param orgTrxNo 原支付流水号
     */
    public void setOrgTrxNo(String orgTrxNo) {
        this.orgTrxNo = orgTrxNo;
    }

    /**
     * 获取原银行订单号
     *
     * @return org_bank_order_no - 原银行订单号
     */
    public String getOrgBankOrderNo() {
        return orgBankOrderNo;
    }

    /**
     * 设置原银行订单号
     *
     * @param orgBankOrderNo 原银行订单号
     */
    public void setOrgBankOrderNo(String orgBankOrderNo) {
        this.orgBankOrderNo = orgBankOrderNo;
    }

    /**
     * 获取原银行流水号
     *
     * @return org_bank_trx_no - 原银行流水号
     */
    public String getOrgBankTrxNo() {
        return orgBankTrxNo;
    }

    /**
     * 设置原银行流水号
     *
     * @param orgBankTrxNo 原银行流水号
     */
    public void setOrgBankTrxNo(String orgBankTrxNo) {
        this.orgBankTrxNo = orgBankTrxNo;
    }

    /**
     * 获取商家名称
     *
     * @return merchant_name - 商家名称
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * 设置商家名称
     *
     * @param merchantName 商家名称
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    /**
     * 获取商家编号
     *
     * @return merchant_no - 商家编号
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 设置商家编号
     *
     * @param merchantNo 商家编号
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    /**
     * 获取原商品名称
     *
     * @return org_product_name - 原商品名称
     */
    public String getOrgProductName() {
        return orgProductName;
    }

    /**
     * 设置原商品名称
     *
     * @param orgProductName 原商品名称
     */
    public void setOrgProductName(String orgProductName) {
        this.orgProductName = orgProductName;
    }

    /**
     * 获取原业务类型
     *
     * @return org_biz_type - 原业务类型
     */
    public String getOrgBizType() {
        return orgBizType;
    }

    /**
     * 设置原业务类型
     *
     * @param orgBizType 原业务类型
     */
    public void setOrgBizType(String orgBizType) {
        this.orgBizType = orgBizType;
    }

    /**
     * 获取原支付方式类型
     *
     * @return org_payment_type - 原支付方式类型
     */
    public String getOrgPaymentType() {
        return orgPaymentType;
    }

    /**
     * 设置原支付方式类型
     *
     * @param orgPaymentType 原支付方式类型
     */
    public void setOrgPaymentType(String orgPaymentType) {
        this.orgPaymentType = orgPaymentType;
    }

    /**
     * 获取订单退款金额
     *
     * @return refund_amount - 订单退款金额
     */
    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    /**
     * 设置订单退款金额
     *
     * @param refundAmount 订单退款金额
     */
    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    /**
     * 获取退款流水号
     *
     * @return refund_trx_no - 退款流水号
     */
    public String getRefundTrxNo() {
        return refundTrxNo;
    }

    /**
     * 设置退款流水号
     *
     * @param refundTrxNo 退款流水号
     */
    public void setRefundTrxNo(String refundTrxNo) {
        this.refundTrxNo = refundTrxNo;
    }

    /**
     * 获取退款订单号
     *
     * @return refund_order_no - 退款订单号
     */
    public String getRefundOrderNo() {
        return refundOrderNo;
    }

    /**
     * 设置退款订单号
     *
     * @param refundOrderNo 退款订单号
     */
    public void setRefundOrderNo(String refundOrderNo) {
        this.refundOrderNo = refundOrderNo;
    }

    /**
     * 获取银行退款订单号
     *
     * @return bank_refund_order_no - 银行退款订单号
     */
    public String getBankRefundOrderNo() {
        return bankRefundOrderNo;
    }

    /**
     * 设置银行退款订单号
     *
     * @param bankRefundOrderNo 银行退款订单号
     */
    public void setBankRefundOrderNo(String bankRefundOrderNo) {
        this.bankRefundOrderNo = bankRefundOrderNo;
    }

    /**
     * 获取银行退款流水号
     *
     * @return bank_refund_trx_no - 银行退款流水号
     */
    public String getBankRefundTrxNo() {
        return bankRefundTrxNo;
    }

    /**
     * 设置银行退款流水号
     *
     * @param bankRefundTrxNo 银行退款流水号
     */
    public void setBankRefundTrxNo(String bankRefundTrxNo) {
        this.bankRefundTrxNo = bankRefundTrxNo;
    }

    /**
     * 获取退款结果通知url
     *
     * @return result_notify_url - 退款结果通知url
     */
    public String getResultNotifyUrl() {
        return resultNotifyUrl;
    }

    /**
     * 设置退款结果通知url
     *
     * @param resultNotifyUrl 退款结果通知url
     */
    public void setResultNotifyUrl(String resultNotifyUrl) {
        this.resultNotifyUrl = resultNotifyUrl;
    }

    /**
     * 获取退款状态
     *
     * @return refund_status - 退款状态
     */
    public String getRefundStatus() {
        return refundStatus;
    }

    /**
     * 设置退款状态
     *
     * @param refundStatus 退款状态
     */
    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    /**
     * 获取退款来源（分发平台）
     *
     * @return refund_from - 退款来源（分发平台）
     */
    public String getRefundFrom() {
        return refundFrom;
    }

    /**
     * 设置退款来源（分发平台）
     *
     * @param refundFrom 退款来源（分发平台）
     */
    public void setRefundFrom(String refundFrom) {
        this.refundFrom = refundFrom;
    }

    /**
     * 获取退款方式
     *
     * @return refund_way - 退款方式
     */
    public String getRefundWay() {
        return refundWay;
    }

    /**
     * 设置退款方式
     *
     * @param refundWay 退款方式
     */
    public void setRefundWay(String refundWay) {
        this.refundWay = refundWay;
    }

    /**
     * 获取退款请求时间
     *
     * @return refund_request_time - 退款请求时间
     */
    public Date getRefundRequestTime() {
        return refundRequestTime;
    }

    /**
     * 设置退款请求时间
     *
     * @param refundRequestTime 退款请求时间
     */
    public void setRefundRequestTime(Date refundRequestTime) {
        this.refundRequestTime = refundRequestTime;
    }

    /**
     * 获取 退款成功时间
     *
     * @return refund_success_time -  退款成功时间
     */
    public Date getRefundSuccessTime() {
        return refundSuccessTime;
    }

    /**
     * 设置 退款成功时间
     *
     * @param refundSuccessTime  退款成功时间
     */
    public void setRefundSuccessTime(Date refundSuccessTime) {
        this.refundSuccessTime = refundSuccessTime;
    }

    /**
     * 获取退款完成时间
     *
     * @return refund_complete_time - 退款完成时间
     */
    public Date getRefundCompleteTime() {
        return refundCompleteTime;
    }

    /**
     * 设置退款完成时间
     *
     * @param refundCompleteTime 退款完成时间
     */
    public void setRefundCompleteTime(Date refundCompleteTime) {
        this.refundCompleteTime = refundCompleteTime;
    }

    /**
     * 获取退款请求,申请人登录名
     *
     * @return request_apply_user_id - 退款请求,申请人登录名
     */
    public String getRequestApplyUserId() {
        return requestApplyUserId;
    }

    /**
     * 设置退款请求,申请人登录名
     *
     * @param requestApplyUserId 退款请求,申请人登录名
     */
    public void setRequestApplyUserId(String requestApplyUserId) {
        this.requestApplyUserId = requestApplyUserId;
    }

    /**
     * 获取退款请求,申请人姓名
     *
     * @return request_apply_user_name - 退款请求,申请人姓名
     */
    public String getRequestApplyUserName() {
        return requestApplyUserName;
    }

    /**
     * 设置退款请求,申请人姓名
     *
     * @param requestApplyUserName 退款请求,申请人姓名
     */
    public void setRequestApplyUserName(String requestApplyUserName) {
        this.requestApplyUserName = requestApplyUserName;
    }

    /**
     * 获取退款原因
     *
     * @return refund_reason - 退款原因
     */
    public String getRefundReason() {
        return refundReason;
    }

    /**
     * 设置退款原因
     *
     * @param refundReason 退款原因
     */
    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
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
}