package com.zhang.itoken.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
/**
 * @Author 张宏运
 * @Date  2019-04-25 14:19
 * 配置中心
 * port：8888
 */
@EnableEurekaClient
@EnableConfigServer
@SpringBootApplication
public class ItokenConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItokenConfigApplication.class, args);
    }

}
