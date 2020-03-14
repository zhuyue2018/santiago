package com.santiago.charging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.santiago.charging"})
public class ChargingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChargingApplication.class, args);
    }

}
