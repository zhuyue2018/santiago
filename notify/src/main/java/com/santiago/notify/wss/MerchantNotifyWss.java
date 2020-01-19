package com.santiago.notify.wss;

import com.santiago.commons.dto.resp.UnionResp;
import com.santiago.commons.dto.resp.UnionResult;
import com.santiago.notify.entity.domain.NotifyRecord;
import com.santiago.notify.mapper.NotifyRecordMapper;
import com.santiago.notify.service.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class MerchantNotifyWss {
    @Autowired
    NotifyService notifyService;
    @Autowired
    NotifyRecordMapper notifyRecordMapper;

    public UnionResult insertNotifyRecord(NotifyRecord notifyRecord) {
        notifyRecordMapper.insert(notifyRecord);
        return new UnionResult(200, "notify recode insert success!");
    }

    public UnionResult pageNotifyRecord(){
        return null;
    }

    @RequestMapping(value = "/notify")
    public String doNotify(NotifyRecord notifyRecord) {
        return "notify";
    }

    @RequestMapping(value = "/batchNotify")
    public String batchNotify() {

        return "batchNotify";
    }

    public void updateRecord(NotifyRecord notifyRecord) {
        notifyRecordMapper.updateByPrimaryKey(notifyRecord);
    }
}
