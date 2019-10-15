package com.santiago.notify.entity.domain;

import java.util.Date;

@Table(name = "rp_notify_record_log")
public class RpNotifyRecordLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
     * 最后修改时间
     */
    @Column(name = "gmt_modified")
    private Date gmtModified;

    @Column(name = "gmt_create")
    private Date gmtCreate;

    @Column(name = "notify_id")
    private Long notifyId;

    private String request;

    private String response;

    @Column(name = "merchant_no")
    private String merchantNo;

    /**
     * 商户订单号
     */
    @Column(name = "merchant_order_no")
    private String merchantOrderNo;

    /**
     * http状态
     */
    @Column(name = "http_status")
    private String httpStatus;

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
     * @return notify_id
     */
    public Long getNotifyId() {
        return notifyId;
    }

    /**
     * @param notifyId
     */
    public void setNotifyId(Long notifyId) {
        this.notifyId = notifyId;
    }

    /**
     * @return request
     */
    public String getRequest() {
        return request;
    }

    /**
     * @param request
     */
    public void setRequest(String request) {
        this.request = request;
    }

    /**
     * @return response
     */
    public String getResponse() {
        return response;
    }

    /**
     * @param response
     */
    public void setResponse(String response) {
        this.response = response;
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
     * 获取http状态
     *
     * @return http_status - http状态
     */
    public String getHttpStatus() {
        return httpStatus;
    }

    /**
     * 设置http状态
     *
     * @param httpStatus http状态
     */
    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }
}