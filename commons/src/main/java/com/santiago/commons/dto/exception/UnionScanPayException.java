package com.santiago.commons.dto.exception;

/**
 * @Author: lxy
 * @Date: 2019/7/15 10:39
 * @Desc: 银联网页扫码支付异常
 */
public class UnionScanPayException extends Exception {
    private static final long serialVersionUID = 2214381471513460742L;

    /**
     * 自定义错误讯息.
     */
    private String exceptionMsg;
    /**
     * 错误代码.
     */
    private String errCode;

    /**
     * 错误代码描述.
     */
    private String errCodeDes;

    /**
     * 银联支付返回的结果xml字符串.
     */
    private String xmlString;

    public UnionScanPayException() {
        super();
    }

    public UnionScanPayException(String message) {
        super(message);
        this.exceptionMsg = message;
    }

    public UnionScanPayException(String errCode, String errCodeDes) {
        super("errCode:" + errCode + ",errCodeDes:" + errCode);
        this.errCode = errCode;
        this.errCodeDes = errCodeDes;
    }

    public UnionScanPayException(String errCode, String errCodeDes, String xmlString) {
        super("errCode:" + errCode + ",errCodeDes:" + errCode + ",xmlString:" + xmlString);
        this.errCode = errCode;
        this.errCodeDes = errCodeDes;
        this.xmlString = xmlString;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }

    public String getXmlString() {
        return xmlString;
    }

    public void setXmlString(String xmlString) {
        this.xmlString = xmlString;
    }


}