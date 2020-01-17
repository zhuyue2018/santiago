package com.santiago.message.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 *   报文重发表
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class MsgRetryDO {
    /**
     * 主键
     */
    private BigDecimal id;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 0:HTTP 1:KAFKA
     */
    private String transferType;

    /**
     * 系统节点编码,表示此数据来源于哪个系统
     */
    private String systemCode;

    /**
     * 发送地址
     */
    private String url;

    /**
     * mq topic
     */
    private String topic;

    /**
     * 接口服务唯一标识
     */
    private String msgCode;

    /**
     * 报文
     */
    private String msgContent;

    /**
     * 重发状态 S:发送成功 F:发送失败
     */
    private String status;

    /**
     * 初始化为0,最多发送n次
     */
    private BigDecimal retryCount;

    /**
     * 最近一次的发送时间
     */
    private Date lastSendTime;

    /**
     * 记录创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 记录逻辑删除标识；0--正常，1--删除
     */
    private String deleteFlag;

    /**
     * 删除信息
     */
    private String deleteInfo;

    /**
     * 数据类型
     */
    private String msgContentType;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType == null ? null : transferType.trim();
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode == null ? null : systemCode.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic == null ? null : topic.trim();
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode == null ? null : msgCode.trim();
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent == null ? null : msgContent.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public BigDecimal getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(BigDecimal retryCount) {
        this.retryCount = retryCount;
    }

    public Date getLastSendTime() {
        return lastSendTime;
    }

    public void setLastSendTime(Date lastSendTime) {
        this.lastSendTime = lastSendTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag == null ? null : deleteFlag.trim();
    }

    public String getDeleteInfo() {
        return deleteInfo;
    }

    public void setDeleteInfo(String deleteInfo) {
        this.deleteInfo = deleteInfo == null ? null : deleteInfo.trim();
    }

    public String getMsgContentType() {
        return msgContentType;
    }

    public void setMsgContentType(String msgContentType) {
        this.msgContentType = msgContentType;
    }
}