package com.santiago.api;

import org.springframework.stereotype.Component;

@Component
public class ApiHystrix implements NotifyApi {
    @Override
    public String batchNotify() {
        return "服务不可用";
    }
}