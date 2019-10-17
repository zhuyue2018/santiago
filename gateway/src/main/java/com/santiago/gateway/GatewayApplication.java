package com.santiago.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {"com.santiago.gateway", "com.santiago.core", "com.santiago.notify", "com.santiago.channel"})
@MapperScan(basePackages = {"com.santiago.core.mapper", "com.santiago.notify.mapper", ""})
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
