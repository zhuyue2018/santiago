package com.santiago.notify.service;


import com.santiago.notify.entity.domain.NotifyRecord;

public interface NotifyService {
    void insertNotifyRecord(NotifyRecord record);
    void doNotify(NotifyRecord record);
}
