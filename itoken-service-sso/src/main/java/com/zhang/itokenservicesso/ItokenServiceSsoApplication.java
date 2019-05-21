package com.zhang.itokenservicesso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author 张宏运
 * @Date  2019-05-20 17:14
 */
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@MapperScan(basePackages = "com.zhang.itokenservicesso.mapper")
public class ItokenServiceSsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItokenServiceSsoApplication.class, args);
    }

}
