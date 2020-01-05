package com.santiago.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "notify", fallback = ApiHystrix.class)
public interface NotifyApi {
    @RequestMapping("/batchNotify")
    String batchNotify();
}
