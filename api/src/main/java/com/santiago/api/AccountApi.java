package com.santiago.api;

import com.santiago.api.dto.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "account")
public interface AccountApi {
    @PostMapping("/account")
    public void account();
    @PostMapping("/account/create")
    public void create(@RequestParam String accountNo, @RequestParam String merchantNo);
    @GetMapping("/accounts")
    List<Account> listAll();
}
