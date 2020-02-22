package com.santiago.account.kafka;

import com.santiago.account.entity.domain.TransactionDTO;
import com.santiago.commons.util.KryoSerializer;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

public class TransactionDeserializer implements Deserializer<TransactionDTO> {
    KryoSerializer kryoSerializer = new KryoSerializer(TransactionDTO.class);

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public TransactionDTO deserialize(String topic, byte[] bytes) {
        TransactionDTO transactionDTO = null;
        ByteArrayInputStream inputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            inputStream = new ByteArrayInputStream(bytes);
            objectInputStream = new ObjectInputStream(inputStream);
            transactionDTO = (TransactionDTO)objectInputStream.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(objectInputStream != null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return transactionDTO;
    }

    @Override
    public void close() {

    }
}