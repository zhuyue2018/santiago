package com.santiago.remit.controller;

import com.santiago.api.RedisApi;
import com.santiago.remit.dto.CheckConfig;
import com.santiago.remit.dto.Order;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Set;

/**
 * @program: dependency
 * @description: 疑似重复出款挡板
 * @author: zhuyue
 * @create: 2020-01-19 16:52
 **/
public class CheckCtrl {
    @Autowired
    RedisApi redisApi;
    public boolean check(Order order, CheckConfig checkConfig) {
        String key = createKey(order);
        Set<String> keys = redisApi.getLike(key + "*");
        int count = calculateCount(order.getAmount());
        if (keys.size() > count) {
            return false;
        } else {
            redisApi.put(key + currentTimeStr, checkConfig.getExpire());
            return true;
        }
    }

    private int calculateCount(BigDecimal amount) {
        if (amount.compareTo(new BigDecimal("100")) < 0) {
            return 5;
        } else if (amount.compareTo(new BigDecimal("1000")) < 0) {
            return 2;
        } else {
            return 1;
        }
    }

    private String createKey(Order order) {
        return order.getPayerNo() + order.getPayeeNo() + order.getAmount().toPlainString();
    }
}
