package com.santiago.core.wss;

import com.zhuyue.pay0929.commons.dto.resp.SimpleResponse;
import com.zhuyue.pay0929.core.entity.domain.RpUserPayConfig;
import com.zhuyue.pay0929.core.service.UserPayConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController(value = "/userPayConfig")
public class UserPayConfigWss {
    @Autowired
    UserPayConfigService userPayConfigService;

    @PutMapping()
    public SimpleResponse insert(@RequestBody  RpUserPayConfig userPayConfig) {
        userPayConfigService.insert(userPayConfig);
        return new SimpleResponse("000000", "添加用户支付配置信息成功");
    }

    @PostMapping(value = "/{payKey}")
    public RpUserPayConfig queryByPayKey(@PathVariable(value = "payKey") String payKey) {
        return userPayConfigService.getByPayKey(payKey);
    }

}
