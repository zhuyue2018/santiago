package com.santiago.message.service.impl;

import com.santiago.message.service.KafkaSendResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

@Service
public class DefaultKafkaSendResultHandler implements KafkaSendResultHandler {
    private static final Logger logger = LoggerFactory.getLogger(DefaultKafkaSendResultHandler.class);

    public DefaultKafkaSendResultHandler() {
    }
    @Override
    public <T> void handleSuccess(String topic, MessageHeaders messageHeaders, T kafkaMsg) {
        logger.info("topic {} headers:{} msg:{} send success ", new Object[]{topic, messageHeaders, kafkaMsg});
    }
    @Override
    public <T> void handleFailure(String topic, MessageHeaders messageHeaders, T kafkaMsg) {
        logger.error("topic {} headers:{} msg:{} send fail ", new Object[]{topic, messageHeaders, kafkaMsg});
    }
}