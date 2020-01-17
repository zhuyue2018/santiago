package com.santiago.message.service;

import java.util.concurrent.ExecutionException;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.util.concurrent.ListenableFuture;

public interface KafkaTemplateService {
    <T> ListenableFuture<SendResult<String, String>> send(String var1, T var2);

    <T> ListenableFuture<SendResult<String, String>> send(String var1, String var2, T var3);

    <T> ListenableFuture<SendResult<String, String>> send(String var1, GenericMessage<T> var2);

    <T> String syncSend(String var1, T var2) throws ExecutionException, InterruptedException;

    <T> String syncSend(String var1, String var2, T var3) throws ExecutionException, InterruptedException;
}
