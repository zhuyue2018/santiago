package com.santiago.api;

import org.springframework.stereotype.Component;

@Component
public class NotifyApiHystrix implements NotifyApi {
    @Override
    public String batchNotify() {
        return "批量回调通知服务不可用";
    }
}