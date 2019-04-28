package com.zhang.itoken.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
/**
 * @Author 张宏运
 * @Date  2019-04-25 14:33
 * 注册中心
 * port：8761
 */
@EnableEurekaServer
@SpringBootApplication
public class ItokenEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItokenEurekaApplication.class, args);
    }

}
