package com.santiago.message.demo;

import com.santiago.commons.util.JsonUtil;
import com.santiago.message.domain.KafkaEventPushRequest;
import com.santiago.message.domain.KafkaPushEventDto;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class QueryResultEventReceiveListener {
    private static final Logger logger = LoggerFactory.getLogger(QueryResultEventReceiveListener.class);


    @KafkaListener(id = "query-result-receive", topics = {EasipayKafkaTopics.Q_REC_QUERY})
    public void listen(ConsumerRecord<?, ?> record,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(EasipayKafkaHeaders.MSG_CODE) String msgCode) throws Exception {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            logger.info("topic {} msgCode {} handle", topic, msgCode);
            EventReceiveKafkaDefine relayKafkaDefine = EventReceiveKafkaDefine.getByMsgCode(msgCode);
            if (relayKafkaDefine == null) {
                logger.error("topic {} msgCode {} handle", topic, msgCode);
                return;
            }
            Object object = kafkaMessage.get();
            KafkaEventPushRequest request = (KafkaEventPushRequest) object;
            KafkaPushEventDto pushEventDto = request.getPushEventDto();
            switch (relayKafkaDefine) {
                case UNION_WEB_ORDER_QUERY:
//                    QueryOrderResult queryOrderResult = pushEventDto.getMsg(QueryOrderResult.class);
//                    orderQueryResultEventReceiveService.handleEvent(queryOrderResult);
                    break;
                case UNION_WEB_REFUND_QUERY:
//                    RefundQueryResult refundQueryResult = pushEventDto.getMsg(RefundQueryResult.class);
//                    refundQueryResultEventReceiveService.handleEvent(refundQueryResult);
                    break;
                default:
                    break;
            }
            Object message = kafkaMessage.get();
            logger.info("----------------- record ={} topic：{}", topic, record);
            logger.info("------------------ message ={} topic：{}", topic, message);
        }
    }

}
