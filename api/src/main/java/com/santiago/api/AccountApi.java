package com.santiago.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "account")
public interface AccountApi {
    @PostMapping("/account")
    public void account();
    @PostMapping("/account/create")
    public void create(@RequestParam String accountNo, @RequestParam String merchantNo);

}
