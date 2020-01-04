package com.santiago.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {"com.santiago.job", "com.santiago.notify", "com.santiago.api"})
@MapperScan(basePackages = {"com.santiago.job", "com.santiago.notify"})
@EnableDiscoveryClient
@EnableFeignClients("com.santiago.api")
public class JobApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobApplication.class, args);
    }
}
