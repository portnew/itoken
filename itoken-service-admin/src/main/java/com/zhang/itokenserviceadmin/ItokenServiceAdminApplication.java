package com.zhang.itokenserviceadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author 张宏运
 * @Date  2019-05-13 17:26
 */

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.zhang.itokenserviceadmin.mapper")

public class ItokenServiceAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItokenServiceAdminApplication.class, args);
    }

}
