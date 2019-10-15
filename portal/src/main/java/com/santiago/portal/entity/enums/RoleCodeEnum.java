package com.santiago.portal.entity.enums;

public enum RoleCodeEnum {
    ADMIN("admin", "管理员"),
    MERCHANT("merchant", "商户"),
    GUEST("guest", "游客");
    private String code;
    private String msg;

    RoleCodeEnum(String code, String msg) {
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
