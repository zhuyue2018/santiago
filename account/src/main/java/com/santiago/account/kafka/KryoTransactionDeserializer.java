package com.santiago.account.kafka;

import com.santiago.account.entity.domain.TransactionDTO;
import com.santiago.commons.util.KryoSerializer;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

public class KryoTransactionDeserializer implements Deserializer<TransactionDTO> {
    KryoSerializer kryoSerializer = new KryoSerializer(TransactionDTO.class);

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public TransactionDTO deserialize(String topic, byte[] bytes) {
        TransactionDTO transactionDTO = (TransactionDTO)kryoSerializer.deserialize(bytes);
        return transactionDTO;
    }

    @Override
    public void close() {

    }
}