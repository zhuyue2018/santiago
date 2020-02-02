package com.santiago.message.demo;

import java.util.stream.Stream;

/**
 * @author sean
 * @Description:
 * @date 19-7-18 下午3:21
 */
public enum EventReceiveKafkaDefine {

    /**
     * 支付通知
     */
    PAY_NOTICE("", EasipayKafkaTopics.Q_REC_PAY),
    /**
     * 关闭订单
     */
    UNION_WEB_ORDER_CLOSE("union-web-order-close", EasipayKafkaTopics.Q_REC_TRANS),
    /**
     * 申请退款
     */
    UNION_WEB_REFUND_APPLY("union-web-refund-apply", EasipayKafkaTopics.Q_REC_TRANS),

    /**
     * 查询订单
     */
    UNION_WEB_ORDER_QUERY("union-web-order-query", EasipayKafkaTopics.Q_REC_QUERY),

    /**
     * 查询退款
     */
    UNION_WEB_REFUND_QUERY("union-web-refund-query", EasipayKafkaTopics.Q_REC_QUERY),
    ;
    private String msgCode;

    private String topic;

    EventReceiveKafkaDefine(String msgCode, String topic) {
        this.msgCode = msgCode;
        this.topic = topic;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public String getTopic() {
        return topic;
    }

    public static EventReceiveKafkaDefine getByMsgCode(String msgCode) {
        return Stream.of(EventReceiveKafkaDefine.values())
            .filter(define -> msgCode.equalsIgnoreCase(define.getMsgCode()))
            .findFirst().orElse(null);
    }
}
