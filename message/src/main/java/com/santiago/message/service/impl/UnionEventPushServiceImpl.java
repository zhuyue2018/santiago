package com.santiago.message.service.impl;

import com.santiago.commons.util.JsonUtil;
import com.santiago.message.domain.KafkaEventPushRequest;
import com.santiago.message.domain.KafkaPushEventDto;
import com.santiago.message.manaer.KafkaEventPushManager;
import com.santiago.message.service.UnionEventPushService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhuyue
 * @Description:
 * @date 19-7-15 下午5:32
 */
@Service
public class UnionEventPushServiceImpl implements UnionEventPushService {

    private static final Logger logger = LoggerFactory.getLogger(UnionEventPushServiceImpl.class);


    @Autowired
    KafkaEventPushManager kafkaEventPushManager;


    @Override
    public <T> void pushEvents(String topic, String msgCode, KafkaPushEventDto eventDto) {
        logger.debug("push kafka topic:{} msgCode:{}  msg:{}", topic,
            msgCode, JsonUtil.obj2JsonStrExcludeNull(eventDto));
        KafkaEventPushRequest kafkaEventPushRequest = new KafkaEventPushRequest();
        kafkaEventPushRequest.setPushEventDto(eventDto);
        kafkaEventPushManager.send(topic, msgCode, kafkaEventPushRequest);
    }

    @Override
    public void pushEvents(String topic, String msgCode, String msg) {
        logger.debug("push kafka topic:{} msgCode:{}  msg:{}", topic,
            msgCode, msg);
        kafkaEventPushManager.send(topic, msgCode, JsonUtil.parseJson(msg, KafkaEventPushRequest.class));
    }

//    @Override
//    public <T> void sendCloseRequest(KafkaPushEventDto eventDto) {
//        pushEvents(UnionRelayKafkaDefine.UNION_WEB_ORDER_CLOSE.getTopic(), UnionRelayKafkaDefine.UNION_WEB_ORDER_CLOSE.getMsgCode(), eventDto);
//    }
//
//    @Override
//    public void sendOrderQueryRequest(KafkaPushEventDto eventDto){
//        pushEvents(UnionRelayKafkaDefine.UNION_WEB_ORDER_QUERY.getTopic(), UnionRelayKafkaDefine.UNION_WEB_ORDER_QUERY.getMsgCode(), eventDto);
//    }
//
//    @Override
//    public void sendRefundQueryRequest(KafkaPushEventDto eventDto){
//        pushEvents(UnionRelayKafkaDefine.UNION_WEB_REFUND_QUERY.getTopic(), UnionRelayKafkaDefine.UNION_WEB_REFUND_QUERY.getMsgCode(), eventDto);
//    }


}
