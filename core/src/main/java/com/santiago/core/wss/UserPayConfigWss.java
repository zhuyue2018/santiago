package com.santiago.core.wss;

import com.santiago.commons.dto.resp.SimpleResponse;
import com.santiago.core.entity.domain.MerchantPayConfig;
import com.santiago.core.service.MerchantPayConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "/userPayConfig")
public class UserPayConfigWss {
    @Autowired
    MerchantPayConfigService merchantPayConfigService;

    @PutMapping()
    public SimpleResponse insert(@RequestBody MerchantPayConfig userPayConfig) {
        merchantPayConfigService.insert(userPayConfig);
        return new SimpleResponse("000000", "添加用户支付配置信息成功");
    }

//    @PostMapping(value = "/{payKey}")
//    public MerchantPayConfig queryByPayKey(@PathVariable(value = "payKey") String payKey) {
//        return merchantPayConfigService.getByPayKey(payKey);
//    }

}