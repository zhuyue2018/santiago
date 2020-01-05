package com.santiago.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "core")
public interface CoreApi {
    @RequestMapping("/defaultUid")
    Long getUid();
}
