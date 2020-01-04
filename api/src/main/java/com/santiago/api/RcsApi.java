package com.santiago.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "rcs")
public interface RcsApi {
    @RequestMapping("/merchantPayConfig/create")
    void createMerchantPayConfig(@RequestParam String merchantNo, @RequestParam String securityRate, @RequestParam String merchantServerIp);

    @RequestMapping("/merchantSettleConfig/create")
    void createMerchantSettleConfig(String merchantNo);
}
