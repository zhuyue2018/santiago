
package com.santiago.message.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 分页请求实体类
 * 根据需要删减无效参数
 * @author: net.easipay.ec.code.generator.CodeGeneratorBuilder
 * @create: 2019-07-24 13:22:54
 **/
public class MsgRetryPageDTO {


    @ApiModelProperty(value = "页码")
    @Positive
    private int currPage;

    @ApiModelProperty(value = "页数")
    @Positive
    private int rows;

    @ApiModelProperty(value = "主键")
    private BigDecimal id;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "0:HTTP 1:KAFKA")
    private String transferType;

    @ApiModelProperty(value = "系统节点编码,表示此数据来源于哪个系统")
    private String systemCode;

    @ApiModelProperty(value = "发送地址")
    private String url;

    @ApiModelProperty(value = "mq topic")
    private String topic;

    @ApiModelProperty(value = "接口服务唯一标识")
    private String msgCode;

    @ApiModelProperty(value = "报文")
    private String msgContent;

    @ApiModelProperty(value = "重发状态 S:发送成功 F:发送失败")
    private String status;

    @ApiModelProperty(value = "初始化为0,最多发送n次")
    private BigDecimal retryCount;

    @ApiModelProperty(value = "最近一次的发送时间")
    private Date lastSendTime;

    @ApiModelProperty(value = "记录创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "记录逻辑删除标识；0--正常，1--删除")
    private String deleteFlag;

    @ApiModelProperty(value = "删除信息")
    private String deleteInfo;


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
        this.orderNo = orderNo;
    }
    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }
    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }
    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        this.deleteFlag = deleteFlag;
    }
    public String getDeleteInfo() {
        return deleteInfo;
    }

    public void setDeleteInfo(String deleteInfo) {
        this.deleteInfo = deleteInfo;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
