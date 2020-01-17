package com.santiago.commons.ftp;

public class ErrorInfo {

    private String errorCode;

    private String errorMsg;

    public ErrorInfo() {
    }

    public ErrorInfo(String errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorInfo(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}