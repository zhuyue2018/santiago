package com.santiago.notify.wss;

import com.santiago.commons.dto.resp.SimpleResponse;
import com.santiago.notify.entity.domain.NotifyRecord;
import com.santiago.notify.mapper.NotifyRecordMapper;
import com.santiago.notify.service.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/notify")
public class MerchantNotifyWss {
    @Autowired
    NotifyService notifyService;
    @Autowired
    NotifyRecordMapper notifyRecordMapper;

    public SimpleResponse insertNotifyRecord(NotifyRecord notifyRecord) {
        notifyRecordMapper.insert(notifyRecord);
        return new SimpleResponse("000000", "notify recode insert success!");
    }

    public SimpleResponse pageNotifyRecord(){
        return null;
    }


    public String doNotify(NotifyRecord notifyRecord) {
        // todo
        return "000000";
    }

    public void updateRecord(NotifyRecord notifyRecord) {
        notifyRecordMapper.updateByPrimaryKey(notifyRecord);
    }
}
