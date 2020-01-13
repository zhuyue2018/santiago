package com.santiago.message.domain;

public enum TransferTypeEnum {

    /**
     * http
     */
    HTTP(0),

    KAFKA(1);

    private int code;

    TransferTypeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}