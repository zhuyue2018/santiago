package com.santiago.commons.dto.exception;

public class BaseException extends RuntimeException {

    private String errCode;

    private String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String errCode, String errMsg) {
        super(errCode + ":" + errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}