package com.santiago.portal.entity.enums;

public enum MenuLevelEnum {
    PRIMENU(1, "一级菜单"), MENU(2, "二级菜单"), BUTTON(3, "按钮");
    private Integer code;
    private String msg;

    MenuLevelEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
