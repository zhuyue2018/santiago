package com.santiago.message.manaer.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.santiago.message.domain.KafkaHeaders;
import com.santiago.message.manaer.KafkaEventPushManager;
import com.santiago.message.service.KafkaTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

/**
 * @author sean
 * @Description:
 * @date 19-7-18 下午4:23
 */
@Service
public class KafkaEventPushManagerImpl implements KafkaEventPushManager {

    @Autowired
    KafkaTemplateService kafkaTemplateService;

    @Override
    public <T> void send(String topic, T kafkaMsg) {
        send(topic, null, kafkaMsg);
    }

    @Override
    public <T> void send(String topic, String msgCode, T kafkaMsg) {
        Map<String, Object> headers = new HashMap(2);
        headers.put(org.springframework.kafka.support.KafkaHeaders.TOPIC, topic);
        if (msgCode != null && !"".equals(msgCode)) {
            headers.put(KafkaHeaders.MSG_CODE, msgCode);
        }
        kafkaTemplateService.send(topic, new GenericMessage(kafkaMsg, headers));
    }

    @Override
    public <T> String syncSend(String topic, T kafkaMsg) throws ExecutionException, InterruptedException {
        return syncSend(topic, null, kafkaMsg);
    }

    @Override
    public <T> String syncSend(String topic, String msgCode, T kafkaMsg)
            throws ExecutionException, InterruptedException {
        return kafkaTemplateService.syncSend(topic, msgCode, kafkaMsg);
    }


}