package com.santiago.rcs.controller;

import com.santiago.commons.dto.resp.UnionResp;
import com.santiago.rcs.entity.domain.MerchantPayConfig;
import com.santiago.rcs.service.MerchantPayConfigService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MerchantPayConfigCtrl {
    @Autowired
    MerchantPayConfigService merchantPayConfigService;

    @PostMapping(value = "/merchantPayConfig")
    public UnionResp insert(String merchantNo, String securityRate, String merchantServerIp) {
        merchantPayConfigService.insert(new MerchantPayConfig());
        return UnionResp.buildResp(null, "000000", "添加用户支付配置信息成功");
    }

//    @PostMapping(value = "/{payKey}")
//    public MerchantPayConfig queryByPayKey(@PathVariable(value = "payKey") String payKey) {
//        return merchantPayConfigService.getByPayKey(payKey);
//    }

}
