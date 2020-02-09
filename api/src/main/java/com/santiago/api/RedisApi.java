package com.santiago.api;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "redis")
public interface RedisApi {

    void setString(String key, String value, long timeout);

    Object getString(String key);

    void deleteString(String token);
}
