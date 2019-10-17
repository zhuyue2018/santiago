package com.santiago.notify.entity.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "notify_record")
public class NotifyRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String version;

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

    @Column(name = "notify_times")
    private Integer notifyTimes;

    @Column(name = "limit_notify_times")
    private Integer limitNotifyTimes;

    /**
     * 0:未通知 1:成功 2:失败
     */
    private String status;

    private String url;

    /**
     * 通知类型
     */
    @Column(name = "notify_type")
    private String notifyType;

    @Column(name = "merchant_no")
    private String merchantNo;

    @Column(name = "merchant_order_no")
    private String merchantOrderNo;

    /**
     * 1:支付成功 2:支付异常
     */
    @Column(name = "order_status")
    private String orderStatus;

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
     * @return notify_times
     */
    public Integer getNotifyTimes() {
        return notifyTimes;
    }

    /**
     * @param notifyTimes
     */
    public void setNotifyTimes(Integer notifyTimes) {
        this.notifyTimes = notifyTimes;
    }

    /**
     * @return limit_notify_times
     */
    public Integer getLimitNotifyTimes() {
        return limitNotifyTimes;
    }

    /**
     * @param limitNotifyTimes
     */
    public void setLimitNotifyTimes(Integer limitNotifyTimes) {
        this.limitNotifyTimes = limitNotifyTimes;
    }

    /**
     * 获取0:未通知 1:成功 2:失败
     *
     * @return status - 0:未通知 1:成功 2:失败
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置0:未通知 1:成功 2:失败
     *
     * @param status 0:未通知 1:成功 2:失败
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取通知类型
     *
     * @return notify_type - 通知类型
     */
    public String getNotifyType() {
        return notifyType;
    }

    /**
     * 设置通知类型
     *
     * @param notifyType 通知类型
     */
    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
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
     * @return merchant_order_no
     */
    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    /**
     * @param merchantOrderNo
     */
    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }

    /**
     * 获取1:支付成功 2:支付异常
     *
     * @return order_status - 1:支付成功 2:支付异常
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置1:支付成功 2:支付异常
     *
     * @param orderStatus 1:支付成功 2:支付异常
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}