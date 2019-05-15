package com.zhang.itokenwebadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author 张宏运
 * @Date  2019-05-15 17:02
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ItokenWebAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItokenWebAdminApplication.class, args);
    }

}
