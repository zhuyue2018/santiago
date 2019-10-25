package com.santiago.commons.enums;

public enum VersionEnum {
    INIT("1.0.0", "最初版本");
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    VersionEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
