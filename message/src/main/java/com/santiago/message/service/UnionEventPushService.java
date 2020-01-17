package com.santiago.message.service;


import com.santiago.message.domain.KafkaPushEventDto;

/**
 * @author zhuyue
 * @Description:
 * @date 19-7-15 下午5:32
 */
public interface UnionEventPushService {

    /**
     *
     * @param topic
     * @param eventDto
     */
    public <T> void pushEvents(String topic, String msgCode, KafkaPushEventDto eventDto);

    /**
     *
     * @param topic
     * @param msg
     */
    public void pushEvents(String topic, String msgCode, String msg);



}
