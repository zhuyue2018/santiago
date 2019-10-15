//package com.santiago.commons.dto.req;
//
//
//import io.swagger.annotations.ApiModelProperty;
//
//import javax.validation.constraints.NotNull;
//
//public abstract class BaseJsonRequest {
//
//    @ApiModelProperty(value = "接口版本号", required = true)
//    @NotNull(message = "msgVersion must be not null")
//    private String msgVersion;
//
//    @ApiModelProperty(value = "接口定义代码", required = true)
//    @NotNull(message = "msgCode must be not null")
//    private String msgCode;
//
//    @ApiModelProperty(value = "发送方系统节点代码", required = true)
//    @NotNull(message = "msgSender must be not null")
//    private String msgSender;
//
//    @ApiModelProperty(value = "发送方密钥")
////    @NotNull(message = "msgSenderKey must be not null")
//    private String msgSenderKey;
//
//    @ApiModelProperty(value = "接收方系统节点代码", required = true)
//    @NotNull(message = "msgReceiver must be not null")
//    private String msgReceiver;
//
//    @ApiModelProperty(value = "接收方密钥", required = true)
////    @NotNull(message = "msgReceiverKey must be not null")
//    private String msgReceiverKey;
//
//    @ApiModelProperty(value = "报文流水号", required = true)
//    @NotNull(message = "msgSerialNo must be not null")
//    private String msgSerialNo;
//
//    @ApiModelProperty(value = "业务内容", required = true, example = "eyJvcmdDb2RlIjoiMTIzNDU2In0=")
//    @NotNull(message = "msgContent must be not null")
//    private String msgContent;
//
//    @ApiModelProperty(value = "对称加密密钥密文", required = true, example = "H4sIAAAAAAAAAA2NtxHAQAjAViKHEgzsP5JftU4CQetPMdkkbr8Iab6EywABdZShlrrlVl2WTge8Bwpnbnkb39qYALAGPqePrsbSWWjULvgUGPGzxeJU1sxKfBu+0VcVj/YQx/rKOU7eED7ZBXk+yazzzHwM021SSf4ifuF0LWa8RtrxrXSXExTxCDeTHR79vYnoz+IAAAA==")
////    @NotNull(message = "msgEncKey must be not null")
//    private String msgEncKey;
//
//    @ApiModelProperty(value = "异步通知地址", required = true)
//    private String notifyUrl;
//
//    @ApiModelProperty(value = "请求时间", required = true)
//    @NotNull(message = "timestamp must be not null")
//    private String timestamp;
//
//    private String digest;
//
//    private String sign;
//
//    private String encryptFlag;
//
//    private String signFlag;
//
//    private String gzipFlag;
//
//    public String getMsgVersion() {
//        return msgVersion;
//    }
//
//    public void setMsgVersion(String msgVersion) {
//        this.msgVersion = msgVersion;
//    }
//
//    public String getMsgCode() {
//        return msgCode;
//    }
//
//    public void setMsgCode(String msgCode) {
//        this.msgCode = msgCode;
//    }
//
//    public String getMsgSender() {
//        return msgSender;
//    }
//
//    public void setMsgSender(String msgSender) {
//        this.msgSender = msgSender;
//    }
//
//    public String getMsgSenderKey() {
//        return msgSenderKey;
//    }
//
//    public void setMsgSenderKey(String msgSenderKey) {
//        this.msgSenderKey = msgSenderKey;
//    }
//
//    public String getMsgReceiver() {
//        return msgReceiver;
//    }
//
//    public void setMsgReceiver(String msgReceiver) {
//        this.msgReceiver = msgReceiver;
//    }
//
//    public String getMsgReceiverKey() {
//        return msgReceiverKey;
//    }
//
//    public void setMsgReceiverKey(String msgReceiverKey) {
//        this.msgReceiverKey = msgReceiverKey;
//    }
//
//    public String getMsgSerialNo() {
//        return msgSerialNo;
//    }
//
//    public void setMsgSerialNo(String msgSerialNo) {
//        this.msgSerialNo = msgSerialNo;
//    }
//
//    public String getMsgContent() {
//        return msgContent;
//    }
//
//    public void setMsgContent(String msgContent) {
//        this.msgContent = msgContent;
//    }
//
//    public String getMsgEncKey() {
//        return msgEncKey;
//    }
//
//    public void setMsgEncKey(String msgEncKey) {
//        this.msgEncKey = msgEncKey;
//    }
//
//    public String getNotifyUrl() {
//        return notifyUrl;
//    }
//
//    public void setNotifyUrl(String notifyUrl) {
//        this.notifyUrl = notifyUrl;
//    }
//
//    public String getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(String timestamp) {
//        this.timestamp = timestamp;
//    }
//
//    public String getDigest() {
//        return digest;
//    }
//
//    public void setDigest(String digest) {
//        this.digest = digest;
//    }
//
//    public String getSign() {
//        return sign;
//    }
//
//    public void setSign(String sign) {
//        this.sign = sign;
//    }
//
//    public String getEncryptFlag() {
//        return encryptFlag;
//    }
//
//    public void setEncryptFlag(String encryptFlag) {
//        this.encryptFlag = encryptFlag;
//    }
//
//    public String getSignFlag() {
//        return signFlag;
//    }
//
//    public void setSignFlag(String signFlag) {
//        this.signFlag = signFlag;
//    }
//
//    public String getGzipFlag() {
//        return gzipFlag;
//    }
//
//    public void setGzipFlag(String gzipFlag) {
//        this.gzipFlag = gzipFlag;
//    }
//}
