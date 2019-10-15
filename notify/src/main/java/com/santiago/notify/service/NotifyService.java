package com.santiago.notify.service;

import com.zhuyue.pay0929.notify.entity.domain.RpNotifyRecord;

public interface NotifyService {
    void insertNotifyRecord(RpNotifyRecord record);
    void doNotify(RpNotifyRecord record);
}
