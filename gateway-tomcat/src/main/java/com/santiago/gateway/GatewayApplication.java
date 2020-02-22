package com.santiago.gateway;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {"com.santiago.gateway", "com.santiago.commons"})
//@MapperScan(basePackages = {"com.santiago.core.mapper", "com.santiago.notify.mapper"})
@NacosPropertySource(dataId = "gateway", autoRefreshed = true)
//@EnableDiscoveryClient
//@EnableFeignClients("com.santiago.api")
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
//        Undertow server = Undertow.builder()
//                .addHttpListener(8080, "localhost")
//                .setHandler(new HttpHandler() {
//                    @Override
//                    public void handleRequest(final HttpServerExchange exchange) throws Exception {
//                        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
//                        exchange.getResponseSender().send("Hello World");
//                    }
//                }).build();
//        server.start();
    }
}
