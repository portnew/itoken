package com.zhang.itokenserviceredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
/**
 * @Author 张宏运
 * @Date  2019-05-18 9:38
 */
@EnableEurekaClient
@SpringBootApplication
public class ItokenServiceRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItokenServiceRedisApplication.class, args);
    }

}
