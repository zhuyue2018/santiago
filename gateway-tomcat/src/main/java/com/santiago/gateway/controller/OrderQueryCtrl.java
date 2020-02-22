package com.santiago.gateway.controller;

import com.santiago.commons.dto.resp.UnionResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/order")
public class OrderQueryCtrl {

    @GetMapping(value = "/{merchantNo}/{orderNo}")
    public UnionResp query(@PathVariable(value = "merchantNo") String merchantNo,
                           @PathVariable(value = "orderNo") String orderNo) {
        return null;
    }
}
