package com.santiago.account.kafka;

import com.santiago.account.entity.domain.TransactionDTO;
import com.santiago.commons.util.KryoSerializer;
import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;


public class KryoTransactionSerializer implements Serializer<TransactionDTO> {
    KryoSerializer kryoSerializer = new KryoSerializer(TransactionDTO.class);
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String topic, TransactionDTO transactionDTO) {
        byte[] dataArray = new byte[100];
        kryoSerializer.serialize(transactionDTO, dataArray);
        return dataArray;
    }

    @Override
    public void close() {

    }
}