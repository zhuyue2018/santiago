package com.santiago.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {"com.santiago.core"})
@MapperScan(basePackages = {"com.santiago.core"})
//@NacosPropertySource(dataId = "core", autoRefreshed = true)
@EnableDiscoveryClient
@EnableFeignClients("com.santiago.api")
public class CoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }

}
