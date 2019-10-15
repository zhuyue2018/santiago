package com.santiago.notify.wss;

import com.zhuyue.pay0929.commons.dto.resp.SimpleResponse;
import com.zhuyue.pay0929.notify.entity.domain.RpNotifyRecord;
import com.zhuyue.pay0929.notify.mapper.RpNotifyRecordMapper;
import com.zhuyue.pay0929.notify.service.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/notify")
public class NotifyWss {
    @Autowired
    NotifyService notifyService;
    @Autowired
    RpNotifyRecordMapper notifyRecordMapper;

    public SimpleResponse insertNotifyRecord(RpNotifyRecord notifyRecord) {
        notifyRecordMapper.insert(notifyRecord);
        return new SimpleResponse("000000", "notify recode insert successed!");
    }

    public SimpleResponse pageNotifyRecord(){
        return null;
    }


    public String doNotify(RpNotifyRecord notifyRecord) {
        // todo
        return "000000";
    }

    public void updateRecord(RpNotifyRecord notifyRecord) {
        notifyRecordMapper.updateByPrimaryKey(notifyRecord);
    }
}
