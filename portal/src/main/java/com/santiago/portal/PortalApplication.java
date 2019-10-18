package com.santiago.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {"com.santiago.portal", "com.santiago.core", "com.santiago.notify"})
@MapperScan({"com.santiago.portal.mapper", "com.santiago.core.mapper", "com.santiago.notify.mapper"})
public class PortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class, args);
    }
}
