package com.santiago.portal.service.impl;

import com.santiago.api.RedisApi;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedisTokenService {
    private long timeout = 60 * 60;
    @Autowired
    private RedisApi redisApi;

    // 将token存入在redis
    public String getToken() {
        String token = "token" + System.currentTimeMillis();
        redisApi.setString(token, token, timeout);
        return token;
    }

    public boolean findToken(String tokenKey) {
        String token = (String) redisApi.getString(tokenKey);
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        // token 获取成功后 删除对应tokenMapstoken
        redisApi.deleteString(token);
        return true;
    }

}