package com.santiago.message.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.santiago.message.service.KafkaSendResultHandler;
import com.santiago.message.service.KafkaTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@ConditionalOnProperty({"spring.kafka.bootstrap-servers"})
public class KafkaTemplateServiceImpl implements KafkaTemplateService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaTemplateServiceImpl.class);
    @Autowired
    private KafkaTemplate template;
    @Autowired
    KafkaSendResultHandler kafkaSendResultHandler;

    public KafkaTemplateServiceImpl() {
    }

    @Override
    public <T> ListenableFuture<SendResult<String, String>> send(String topic, T msg) {
        return this.send(topic, (String)null, msg);
    }

    @Override
    public <T> ListenableFuture<SendResult<String, String>> send(String topic, String msgCode, T msg) {
        Map<String, Object> headers = new HashMap(3);
        headers.put("kafka_topic", topic);
        if (msgCode != null && !"".equals(msgCode)) {
            headers.put("easipay_kafka_msgCode", msgCode);
        }

        return this.send(topic, new GenericMessage(msg, headers));
    }
    @Override
    public <T> ListenableFuture<SendResult<String, String>> send(final String topic, final GenericMessage<T> genericMessage) {
        ListenableFuture<SendResult<String, String>> future = this.template.send(genericMessage);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                KafkaTemplateServiceImpl.logger.info("kafka msg send success {}", result.toString());
                KafkaTemplateServiceImpl.this.kafkaSendResultHandler.handleSuccess(topic, genericMessage.getHeaders(), genericMessage.getPayload());
            }

            @Override
            public void onFailure(Throwable ex) {
                KafkaTemplateServiceImpl.this.kafkaSendResultHandler.handleFailure(topic, genericMessage.getHeaders(), genericMessage.getPayload());
                KafkaTemplateServiceImpl.logger.error(ex.getMessage(), ex);
            }
        });
        return future;
    }
    @Override
    public <T> String syncSend(String topic, T msg) throws ExecutionException, InterruptedException {
        return this.syncSend(topic, (String)null, msg);
    }
    @Override
    public <T> String syncSend(String topic, String msgCode, T msg) throws ExecutionException, InterruptedException {
        SendResult result = (SendResult)this.send(topic, msgCode, msg).get();
        return result != null ? "success" : "fail";
    }
}
