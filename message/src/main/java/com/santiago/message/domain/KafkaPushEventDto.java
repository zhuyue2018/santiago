package com.santiago.message.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Map;
import javax.validation.constraints.NotNull;

import com.santiago.commons.util.BeanUtils;

/**
 * @author sean
 * @Description:
 * @date 19-7-24 上午9:13
 */
@JsonInclude(Include.NON_NULL)
public class KafkaPushEventDto {

    private String orderNo;

    private String noticeUrl;

    @NotNull
    private Object msg;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getNoticeUrl() {
        return noticeUrl;
    }

    public void setNoticeUrl(String noticeUrl) {
        this.noticeUrl = noticeUrl;
    }

    public Object getMsg() {
        return msg;
    }

    public <T> T getMsg(Class<T> valueType) throws IllegalAccessException, InstantiationException {
        if (Map.class.isAssignableFrom(this.msg.getClass())) {
            return BeanUtils.mapToBean((Map<String, Object>) this.msg, valueType.newInstance());
        }
        return valueType.newInstance();
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "KafkaPushEventDto{" +
            "orderNo='" + orderNo + '\'' +
            ", noticeUrl='" + noticeUrl + '\'' +
            ", msg=" + msg +
            '}';
    }
}