package com.santiago.job;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {"com.santiago"})
@MapperScan(basePackages = {"com.santiago.notify.mapper", "com.santiago.job.mapper"})
@NacosPropertySource(dataId = "job", autoRefreshed = true)
@EnableDiscoveryClient
@EnableFeignClients("com.santiago.api")
public class JobApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobApplication.class, args);
    }
}
