package com.santiago.core.entity.enums;

public enum PayProductEnum {
    ;
    private String code;
    private String msg;

    PayProductEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
