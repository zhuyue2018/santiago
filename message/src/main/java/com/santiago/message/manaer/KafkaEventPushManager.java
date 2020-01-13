package com.santiago.message.manaer;

import java.util.concurrent.ExecutionException;

/**
 * @author sean
 * @Description:
 * @date 19-7-18 下午4:23
 */
public interface KafkaEventPushManager {

    public <T> void send(String topic, T kafkaMsg);

    public <T> void send(String topic, String msgCode, T kafkaMsg);


    public <T> String syncSend(String topic, T kafkaMsg) throws ExecutionException, InterruptedException;


    public <T> String syncSend(String topic, String msgCode, T kafkaMsg)
        throws ExecutionException, InterruptedException;


}
