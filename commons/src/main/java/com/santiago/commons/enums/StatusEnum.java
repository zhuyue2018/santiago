package com.santiago.commons.enums;

public enum StatusEnum {
    INIT("0", "初始化"),
    SUCCESS("1", "成功"),
    FAILURE("2", "失败"),
    OTHER("9", "其他")
    ;
    private String code;
    private String msg;

    StatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static String getMsgByCode(String code) {
        StatusEnum[] values = StatusEnum.values();
        for (StatusEnum status : values) {
            if (status.getCode().equals(code)) {
                return status.getMsg();
            }
        }
        return null;
    }
}
