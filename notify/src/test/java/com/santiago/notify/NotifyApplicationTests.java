package com.santiago.notify;

import com.santiago.commons.enums.StatusEnum;
import com.santiago.notify.entity.domain.NotifyRecord;
import com.santiago.notify.mapper.NotifyRecordMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NotifyApplicationTests {
    @Autowired
    NotifyRecordMapper notifyRecordMapper;
    @Test
    public void testInsert() {
        NotifyRecord notifyRecord = new NotifyRecord();
        notifyRecord.setVersion("1.0.0");
        notifyRecord.setGmtCreate(new Date());
        notifyRecord.setGmtModified(new Date());
        notifyRecord.setCreater("test");
        notifyRecord.setEditor("test");
        notifyRecord.setNotifyTimes(1);
        notifyRecord.setLimitNotifyTimes(10);
        notifyRecord.setStatus(StatusEnum.INIT.getCode());
        notifyRecord.setUrl("test");
        notifyRecord.setNotifyType("1");
        notifyRecord.setMerchantNo("001");
        notifyRecord.setMerchantOrderNo("123456");
        notifyRecord.setOrderStatus(StatusEnum.SUCCESS.getCode());
        notifyRecordMapper.insert(notifyRecord);
    }

    @Test
    public void contextLoads() {

    }

}
