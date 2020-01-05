package com.santiago.commons.enums;

import org.apache.commons.lang3.StringUtils;

public enum ErrorCodeEnum {
    SUCCESS("000000", "请求成功"),
    DUPLICATED_BIZ_NO("100001", "业务主键重复"),
    PARAMS_ERROR("100002", "参数有误"),
    IP_ERROR("100003", "ip地址不在白名单"),
    SIGN_ERROR("100004", "验证签名错误"),
    USER_PAY_CONFIG_ERROR("100005", "用户支付配置有误"),
    USER_PAY_INFO_ERROR("100006", "用户配置有误"),
    SECURITY_RATING_ERROR("100007", "用户安全级别配置有误"),
    TRADE_STATUS_ERROR("100008", "交易状态异常"),
    SYSTEM_ERROR("999999", "系统内部错误");

    private String code;
    private String msg;

    ErrorCodeEnum(String code, String msg) {
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
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        ErrorCodeEnum[] values = ErrorCodeEnum.values();
        for (ErrorCodeEnum obj : values){
            if (code.equals(obj.getCode())) {
                return obj.getMsg();
            }
        }
        return null;
    }
}
