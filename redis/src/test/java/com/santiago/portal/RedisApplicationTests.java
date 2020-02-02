package com.santiago.portal;

import com.santiago.portal.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {
    @Autowired
    RedisService redisService;
    @Test
    public void available() {
        redisService.set("test", "test1");
        System.out.println(redisService.get("test"));
        redisService.del("test");
        System.out.println(redisService.get("test"));
    }

    @Test
    public void like() {
        redisService.set("test0", "test0");
        redisService.set("test1", "test1");
        Set<String> keys = redisService.getLike("test*");
        System.out.println(keys);
    }



}
