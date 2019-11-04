package com.santiago.core.entity.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "trade_order")
public class TradeOrder {
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
     * 状态(参考枚举:orderstatusenum)
     */
    private String status;

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
     * 订单金额
     */
    @Column(name = "order_amount")
    private BigDecimal orderAmount;

    /**
     * 订单来源
     */
    @Column(name = "order_from")
    private String orderFrom;

    /**
     * 商家名称
     */
    @Column(name = "merchant_name")
    private String merchantName;

    /**
     * 商户编号
     */
    @Column(name = "merchant_no")
    private String merchantNo;

    /**
     * 下单时间
     */
    @Column(name = "order_time")
    private Date orderTime;

    /**
     * 下单日期
     */
    @Column(name = "order_date")
    private Date orderDate;

    /**
     * 下单ip(客户端ip,在网关页面获取)
     */
    @Column(name = "order_ip")
    private String orderIp;

    /**
     * 从哪个页面链接过来的(可用于防诈骗)
     */
    @Column(name = "order_referer_url")
    private String orderRefererUrl;

    /**
     * 页面回调通知url
     */
    @Column(name = "return_url")
    private String returnUrl;

    /**
     * 后台异步通知url
     */
    @Column(name = "notify_url")
    private String notifyUrl;

    /**
     * 订单撤销原因
     */
    @Column(name = "cancel_reason")
    private String cancelReason;

    /**
     * 订单有效期(单位分钟)
     */
    @Column(name = "order_period")
    private Short orderPeriod;

    /**
     * 到期时间
     */
    @Column(name = "expire_time")
    private Date expireTime;

    /**
     * 支付方式编号
     */
    @Column(name = "pay_product_code")
    private String payProductCode;

    /**
     * 支付方式名称
     */
    @Column(name = "pay_product_name")
    private String payProductName;

    /**
     * 支付备注
     */
    private String remark;

    /**
     * 交易业务类型  ：消费、充值等
     */
    @Column(name = "trx_type")
    private String trxType;

    /**
     * 支付流水号
     */
    @Column(name = "trx_no")
    private String trxNo;

    @Column(name = "bank_order_no")
    private String bankOrderNo;

    /**
     * 资金流入类型
     */
    @Column(name = "fund_into_type")
    private String fundIntoType;

    /**
     * 是否退款(100:是,101:否,默认值为:101)
     */
    @Column(name = "is_refund")
    private String isRefund;

    /**
     * 退款次数(默认值为:0)
     */
    @Column(name = "refund_times")
    private Integer refundTimes;

    /**
     * 成功退款总金额
     */
    @Column(name = "success_refund_amount")
    private BigDecimal successRefundAmount;

    private String field1;

    private String field2;

    private String field3;

    private String field4;

    private String field5;

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
     * 获取状态(参考枚举:orderstatusenum)
     *
     * @return status - 状态(参考枚举:orderstatusenum)
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态(参考枚举:orderstatusenum)
     *
     * @param status 状态(参考枚举:orderstatusenum)
     */
    public void setStatus(String status) {
        this.status = status;
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
     * 获取订单来源
     *
     * @return order_from - 订单来源
     */
    public String getOrderFrom() {
        return orderFrom;
    }

    /**
     * 设置订单来源
     *
     * @param orderFrom 订单来源
     */
    public void setOrderFrom(String orderFrom) {
        this.orderFrom = orderFrom;
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
     * 获取商户编号
     *
     * @return merchant_no - 商户编号
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 设置商户编号
     *
     * @param merchantNo 商户编号
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    /**
     * 获取下单时间
     *
     * @return order_time - 下单时间
     */
    public Date getOrderTime() {
        return orderTime;
    }

    /**
     * 设置下单时间
     *
     * @param orderTime 下单时间
     */
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * 获取下单日期
     *
     * @return order_date - 下单日期
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * 设置下单日期
     *
     * @param orderDate 下单日期
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * 获取下单ip(客户端ip,在网关页面获取)
     *
     * @return order_ip - 下单ip(客户端ip,在网关页面获取)
     */
    public String getOrderIp() {
        return orderIp;
    }

    /**
     * 设置下单ip(客户端ip,在网关页面获取)
     *
     * @param orderIp 下单ip(客户端ip,在网关页面获取)
     */
    public void setOrderIp(String orderIp) {
        this.orderIp = orderIp;
    }

    /**
     * 获取从哪个页面链接过来的(可用于防诈骗)
     *
     * @return order_referer_url - 从哪个页面链接过来的(可用于防诈骗)
     */
    public String getOrderRefererUrl() {
        return orderRefererUrl;
    }

    /**
     * 设置从哪个页面链接过来的(可用于防诈骗)
     *
     * @param orderRefererUrl 从哪个页面链接过来的(可用于防诈骗)
     */
    public void setOrderRefererUrl(String orderRefererUrl) {
        this.orderRefererUrl = orderRefererUrl;
    }

    /**
     * 获取页面回调通知url
     *
     * @return return_url - 页面回调通知url
     */
    public String getReturnUrl() {
        return returnUrl;
    }

    /**
     * 设置页面回调通知url
     *
     * @param returnUrl 页面回调通知url
     */
    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    /**
     * 获取后台异步通知url
     *
     * @return notify_url - 后台异步通知url
     */
    public String getNotifyUrl() {
        return notifyUrl;
    }

    /**
     * 设置后台异步通知url
     *
     * @param notifyUrl 后台异步通知url
     */
    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    /**
     * 获取订单撤销原因
     *
     * @return cancel_reason - 订单撤销原因
     */
    public String getCancelReason() {
        return cancelReason;
    }

    /**
     * 设置订单撤销原因
     *
     * @param cancelReason 订单撤销原因
     */
    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    /**
     * 获取订单有效期(单位分钟)
     *
     * @return order_period - 订单有效期(单位分钟)
     */
    public Short getOrderPeriod() {
        return orderPeriod;
    }

    /**
     * 设置订单有效期(单位分钟)
     *
     * @param orderPeriod 订单有效期(单位分钟)
     */
    public void setOrderPeriod(Short orderPeriod) {
        this.orderPeriod = orderPeriod;
    }

    /**
     * 获取到期时间
     *
     * @return expire_time - 到期时间
     */
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * 设置到期时间
     *
     * @param expireTime 到期时间
     */
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * 获取支付方式编号
     *
     * @return pay_product_code - 支付方式编号
     */
    public String getPayProductCode() {
        return payProductCode;
    }

    /**
     * 设置支付方式编号
     *
     * @param payProductCode 支付方式编号
     */
    public void setPayProductCode(String payProductCode) {
        this.payProductCode = payProductCode;
    }

    /**
     * 获取支付方式名称
     *
     * @return pay_product_name - 支付方式名称
     */
    public String getPayProductName() {
        return payProductName;
    }

    /**
     * 设置支付方式名称
     *
     * @param payProductName 支付方式名称
     */
    public void setPayProductName(String payProductName) {
        this.payProductName = payProductName;
    }

    /**
     * 获取支付备注
     *
     * @return remark - 支付备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置支付备注
     *
     * @param remark 支付备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取交易业务类型  ：消费、充值等
     *
     * @return trx_type - 交易业务类型  ：消费、充值等
     */
    public String getTrxType() {
        return trxType;
    }

    /**
     * 设置交易业务类型  ：消费、充值等
     *
     * @param trxType 交易业务类型  ：消费、充值等
     */
    public void setTrxType(String trxType) {
        this.trxType = trxType;
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
     * 获取资金流入类型
     *
     * @return fund_into_type - 资金流入类型
     */
    public String getFundIntoType() {
        return fundIntoType;
    }

    /**
     * 设置资金流入类型
     *
     * @param fundIntoType 资金流入类型
     */
    public void setFundIntoType(String fundIntoType) {
        this.fundIntoType = fundIntoType;
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
    public Integer getRefundTimes() {
        return refundTimes;
    }

    /**
     * 设置退款次数(默认值为:0)
     *
     * @param refundTimes 退款次数(默认值为:0)
     */
    public void setRefundTimes(Integer refundTimes) {
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
     * @return field1
     */
    public String getField1() {
        return field1;
    }

    /**
     * @param field1
     */
    public void setField1(String field1) {
        this.field1 = field1;
    }

    /**
     * @return field2
     */
    public String getField2() {
        return field2;
    }

    /**
     * @param field2
     */
    public void setField2(String field2) {
        this.field2 = field2;
    }

    /**
     * @return field3
     */
    public String getField3() {
        return field3;
    }

    /**
     * @param field3
     */
    public void setField3(String field3) {
        this.field3 = field3;
    }

    /**
     * @return field4
     */
    public String getField4() {
        return field4;
    }

    /**
     * @param field4
     */
    public void setField4(String field4) {
        this.field4 = field4;
    }

    /**
     * @return field5
     */
    public String getField5() {
        return field5;
    }

    /**
     * @param field5
     */
    public void setField5(String field5) {
        this.field5 = field5;
    }
}