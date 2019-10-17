package com.santiago.commons.enums;

public enum  SecurityRatingEnum {
    NONE("none", ""),
    IP("ip", ""),
    SIGN("sign", "")
    ;
    private String code;
    private String msg;

    SecurityRatingEnum(String code, String msg) {
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
