package com.santiago.message.service;

import org.springframework.messaging.MessageHeaders;

public interface KafkaSendResultHandler {
    <T> void handleSuccess(String var1, MessageHeaders var2, T var3);

    <T> void handleFailure(String var1, MessageHeaders var2, T var3);
}