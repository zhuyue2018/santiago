package com.santiago.rcs;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {"com.santiago.portal", "com.santiago.core", "com.santiago.notify", "com.santiago.settlement"})
@MapperScan({"com.santiago.portal.mapper", "com.santiago.core.mapper", "com.santiago.notify.mapper", "com.santiago.settlement.mapper"})
public class RcsApplication {
    public static void main(String[] args) {
        SpringApplication.run(RcsApplication.class, args);
    }
}
