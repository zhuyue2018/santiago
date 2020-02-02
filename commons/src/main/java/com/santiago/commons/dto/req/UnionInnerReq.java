package com.santiago.commons.dto.req;

import com.santiago.commons.util.JsonUtil;
import org.apache.commons.codec.binary.Base64;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @program: sac-fa
 * @description:
 * @author: zhuyue
 * @create: 2019-11-21 10:39
 **/
public class UnionInnerReq {
    @NotNull(message = "msgVersion must be not null")
    @Size(max = 50, min = 1, message = "msgVersion size wrong")
    private String msgVersion;
    @NotNull(message = "msgCode must be not null")
    @Size(max = 50, min = 1, message = "msgCode size wrong")
    private String msgCode;
    @NotNull(message = "msgSerialNo must be not null")
    @Size(max = 50, min = 1, message = "msgSerialNo size wrong")
    private String msgSerialNo;
    @NotNull(message = "msgSender must be not null")
    @Size(max = 50, min = 1, message = "msgSender size wrong")
    private String msgSender;
    private String msgReceiver;
    @NotNull(message = "msgContent must be not null")
    private String msgContent;
    private String timestamp;
    private Sign sign;
    private Encrypt encrypt;
    private Notify notify;
    private Page page;

    public String getContentStr() throws UnsupportedEncodingException {
        return new String((new Base64()).decode(this.getMsgContent()), "UTF-8");
    }

    public <T> T getContent(Class<T> protoType) {
        String decoded = null;
        try {
            decoded = new String((new Base64()).decode(this.getMsgContent()), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return JsonUtil.parseJson(decoded, protoType);
    }

    public static UnionInnerReq create() {
        UnionInnerReq req = new UnionInnerReq();
        return req;
    }

    public String getMsgVersion() {
        return msgVersion;
    }

    public void setMsgVersion(String msgVersion) {
        this.msgVersion = msgVersion;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public String getMsgSerialNo() {
        return msgSerialNo;
    }

    public void setMsgSerialNo(String msgSerialNo) {
        this.msgSerialNo = msgSerialNo;
    }

    public String getMsgSender() {
        return msgSender;
    }

    public void setMsgSender(String msgSender) {
        this.msgSender = msgSender;
    }

    public String getMsgReceiver() {
        return msgReceiver;
    }

    public void setMsgReceiver(String msgReceiver) {
        this.msgReceiver = msgReceiver;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Sign getSign() {
        return sign;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    public Encrypt getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(Encrypt encrypt) {
        this.encrypt = encrypt;
    }

    public Notify getNotify() {
        return notify;
    }

    public void setNotify(Notify notify) {
        this.notify = notify;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "UnionInnerReq{" +
                "msgVersion='" + msgVersion + '\'' +
                ", msgCode='" + msgCode + '\'' +
                ", msgSerialNo='" + msgSerialNo + '\'' +
                ", msgSender='" + msgSender + '\'' +
                ", msgReceiver='" + msgReceiver + '\'' +
                ", msgContent='" + msgContent + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", sign=" + sign +
                ", encrypt=" + encrypt +
                ", notify=" + notify +
                ", page=" + page +
                '}';
    }
}
