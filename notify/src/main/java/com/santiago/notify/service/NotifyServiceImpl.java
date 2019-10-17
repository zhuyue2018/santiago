package com.santiago.notify.service;

import com.santiago.notify.entity.domain.NotifyRecord;
import com.santiago.notify.entity.dto.OrderNotifyRequest;
import com.santiago.notify.mapper.NotifyRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class NotifyServiceImpl implements NotifyService {
    @Autowired
    NotifyRecordMapper notifyRecordMapper;

    @Override
    public void insertNotifyRecord(NotifyRecord record) {
        notifyRecordMapper.insert(record);
    }

    @Override
    public void doNotify(NotifyRecord record) {
        // 通知商户
        String respCode = notifyMerchant(record);
        // 成功则更新status为1 失败则抛出异常
        if ("000000".equals(respCode)) {
            record.setStatus("1");
        }
        record.setNotifyTimes(record.getLimitNotifyTimes() + 1);
        record.setGmtModified(new Date());
        record.setEditor("job");
        notifyRecordMapper.updateByPrimaryKey(record);
    }

    private String notifyMerchant(NotifyRecord record) {
        String url = record.getUrl();
        String merchantNo = record.getMerchantNo();
        String merchantOrderNo = record.getMerchantOrderNo();
        String orderStatus = record.getOrderStatus();
        OrderNotifyRequest orderNotifyRequest = new OrderNotifyRequest(url, merchantNo, merchantOrderNo, orderStatus);
        // todo
        return "000000";
    }
}
