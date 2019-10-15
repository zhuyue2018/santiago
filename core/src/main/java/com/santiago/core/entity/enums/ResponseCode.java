package com.santiago.core.entity.enums;

public enum ResponseCode {

    /**
     * 请求成功
     */
    SUCCESS("000000", "请求成功"),

    SIGN_VERIFY_FAIL("100001", "签名验证出错"),

    /**
     * XX参数不正确(包括不能为空值的参数传输空值、字段长度超过最大数、金额格式不正确、日期格式不正确等)，由于XX原因
     */
    INVALID_ARGUMENT("100005", "参数不正确"),

    INSUFFICIENT_USER_PERMISSIONS("100006", "没有权限访问该服务"),

    /**
     * 流水号已存在(重复流水号或已经接收成功)，调用方一定注意此返回码也代表表示成功
     */
    SERIALNO_EXIST("200001", "流水号已存在"),

    /**
     * 订单号已存在(重复订单号或已经接收成功)，调用方一定注意此返回码也代表表示成功
     */
    ORDER_EXIST("200002", "订单号已存在"),

    /**
     * 交易当前接收阶段校验前置流程阶段是否正确
     */
    ILLEGAL_FLOW("200003", "交易校验当前流程阶段不合法"),

    AMOUNT_LESS_ZERO("200004", "金额不能小于等于0"),

    NUMBER_LESS_ZERO("200005", "数量不能小于或等于0"),

    INVALID_MERCHANT("200006", "企业（或商户）标识无效"),

    ORDER_CLOSED("200007", "订单关闭中或已关闭"),

    ORDER_REPEAT("200008", "订单号重复使用出现字段信息不匹配"),

    ORDER_NOT_EXIST("200009", "订单不存在"),
    /**
     * 请求处理拒绝，当前记录状态不允许处理此请求，当前状态为XX
     */
    REQUEST_DENY("200010", "请求处理拒绝"),

    /**
     * 非法退款金额，退款金额已超出支付总金额
     */
    ILLEGAL_REFUND_AMOUNT("200011", "非法退款金额"),

    /**
     * 退款审核不通过
     */
    REFUND_AUDIT_FAIL("200012", "退款审核不通过"),

    /**
     * 服务未及时响应,导致服务熔断
     */
    SERVICE_CIRCUIT("999998", "服务熔断"),

    /**
     * 服务方内部处理异常无法正常接收数据返回
     */
    SYSTEM_EXCEPTION("999999", "内部系统错误"),;

    private String code;

    private String msg;

    ResponseCode(String code, String msg) {
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