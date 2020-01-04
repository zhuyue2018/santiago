package com.santiago.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "notify", fallback = NotifyApiHystrix.class)
public interface NotifyApi {
    @RequestMapping("/batchNotify")
    public String batchNotify();
}
