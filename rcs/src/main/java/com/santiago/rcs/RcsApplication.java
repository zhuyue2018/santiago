package com.santiago.rcs;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {"com.santiago.rcs"})
@MapperScan({"com.santiago.rcs.mapper"})
@EnableDiscoveryClient
@EnableFeignClients("com.santiago.api")
public class RcsApplication {
    public static void main(String[] args) {
        SpringApplication.run(RcsApplication.class, args);
    }
}
