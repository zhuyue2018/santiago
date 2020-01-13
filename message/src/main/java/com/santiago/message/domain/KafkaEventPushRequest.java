package com.santiago.message.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author sean
 * @Description:
 * @date 19-7-24 上午9:49
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class KafkaEventPushRequest {

    private static final long serialVersionUID = 1L;

    private KafkaPushEventDto kafkaPushEventDto;

    public KafkaPushEventDto getPushEventDto() {
        return kafkaPushEventDto;
    }

    public void setPushEventDto(KafkaPushEventDto kafkaPushEventDto) {
        this.kafkaPushEventDto = kafkaPushEventDto;
    }
}