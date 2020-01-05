package com.santiago.api;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "redis")
public interface RedisApi {

}
