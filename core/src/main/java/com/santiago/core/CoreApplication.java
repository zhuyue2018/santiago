package com.santiago.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(scanBasePackages = "com.zhuyue.pay0929")
//@MapperScan(basePackages = {"com.zhuyue.pay0929.core", "com.zhuyue.pay0929.notify"})
public class CoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }

}
