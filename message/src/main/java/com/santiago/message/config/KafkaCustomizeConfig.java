package com.santiago.message.config;

import com.santiago.message.service.KafkaSendResultHandler;
import com.santiago.message.service.impl.KafkaSendExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class KafkaCustomizeConfig {

    @Bean
    @Primary
    KafkaSendResultHandler KafkaSendExceptionHandler() {
        return new KafkaSendExceptionHandler();
    }
}