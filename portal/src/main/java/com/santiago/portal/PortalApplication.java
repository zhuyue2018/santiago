package com.santiago.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {"com.santiago.portal", "com.santiago.commons", "com.santiago.order.api"})
@MapperScan({"com.santiago.portal.mapper", "com.santiago.commons.mapper"})
@EnableDiscoveryClient
@EnableFeignClients("com.santiago.order.api")
public class PortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class, args);
    }
}
