package com.santiago.api;

import com.santiago.api.dto.MerchantSettleConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "rcs")
public interface RcsApi {
    @RequestMapping("/merchantPayConfig")
    void createMerchantPayConfig(@RequestParam String merchantNo, @RequestParam String securityRate, @RequestParam String merchantServerIp);

    @GetMapping("/merchantSettleConfig/{merchantNo}")
    void createMerchantSettleConfig(@PathVariable String merchantNo);

    @PostMapping("/merchantSettleConfigs")
    List<MerchantSettleConfig> listMerchantSettleConfig(MerchantSettleConfig merchantSettleConfig);
}
