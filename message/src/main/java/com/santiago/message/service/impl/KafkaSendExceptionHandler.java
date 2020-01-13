package com.santiago.message.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import com.santiago.commons.util.JsonUtil;
import com.santiago.message.domain.*;
import com.santiago.message.service.KafkaSendResultHandler;
import com.santiago.message.service.MsgRetryManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.MessageHeaders;

/**
 * @author sean
 * @Description:
 * @date 19-7-19 上午10:53
 */
public class KafkaSendExceptionHandler implements KafkaSendResultHandler {

    private static final Logger logger = LoggerFactory.getLogger(KafkaSendExceptionHandler.class);

    @Autowired
    MsgRetryManager psMsgRetryManager;

    @Value("${spring.application.name}")
    private String application;

    @Override
    public <T> void handleSuccess(String topic, MessageHeaders messageHeaders, T kafkaMsg) {
        logger.debug("handle success {}", this.getClass().getSimpleName());
        if (kafkaMsg instanceof KafkaEventPushRequest) {
            KafkaEventPushRequest request = (KafkaEventPushRequest) kafkaMsg;
            KafkaPushEventDto kafkaPushEventDto = request.getPushEventDto();
            if (StringUtils.isNotEmpty(kafkaPushEventDto.getOrderNo())) {
                logger.debug("update order {}", kafkaPushEventDto.getOrderNo());
                psMsgRetryManager.updateSuccessStatus(kafkaPushEventDto.getOrderNo());
            }
        }
    }

    @Override
    public <T> void handleFailure(String topic, MessageHeaders messageHeaders, T kafkaMsg) {
        logger.debug("handle failure {}", this.getClass().getSimpleName());
        //do save kafka msg for retrying

        if (kafkaMsg instanceof KafkaEventPushRequest) {
            KafkaEventPushRequest request = (KafkaEventPushRequest) kafkaMsg;
            KafkaPushEventDto kafkaPushEventDto = request.getPushEventDto();
            if (StringUtils.isNotEmpty(kafkaPushEventDto.getOrderNo())) {
                MsgRetryDTO existDto = psMsgRetryManager.getByOrderNo(kafkaPushEventDto.getOrderNo());
                if (existDto != null) {
                    psMsgRetryManager.updateRetryCount(existDto);
                } else {
                    psMsgRetryManager
                        .insert(
                            createPsMsgRetryDTO(topic, messageHeaders.get(KafkaHeaders.MSG_CODE, String.class),
                                kafkaPushEventDto.getOrderNo(), kafkaMsg));
                }
            }
        }
    }

    public <T> MsgRetryDTO createPsMsgRetryDTO(String topic, String msgCode, String orderNo, T kafkaMsg) {
        MsgRetryDTO dto = new MsgRetryDTO();
        dto.setTransferType(String.valueOf(TransferTypeEnum.KAFKA.getCode()));
        dto.setOrderNo(orderNo);
        dto.setRetryCount(new BigDecimal(0));
        dto.setSystemCode(application);
        dto.setMsgCode(msgCode);
        dto.setTopic(topic);
        dto.setStatus(MsgRetryStatusEnum.F.name());
        if (kafkaMsg instanceof String) {
            dto.setMsgContent(kafkaMsg.toString());
        } else {
            dto.setMsgContent(JsonUtil.obj2JsonStrExcludeNull(kafkaMsg));
        }
        dto.setLastSendTime(new Date());
        dto.setUpdateTime(new Date());
        dto.setCreateTime(new Date());
        return dto;
    }
}