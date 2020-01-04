package com.santiago.gateway;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {"com.santiago.gateway", "com.santiago.core", "com.santiago.notify", "com.santiago.commons", "com.santiago.api"})
@MapperScan(basePackages = {"com.santiago.core.mapper", "com.santiago.notify.mapper", ""})
@NacosPropertySource(dataId = "gateway", autoRefreshed = true)
@EnableDiscoveryClient
@EnableFeignClients
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
