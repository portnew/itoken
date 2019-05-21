package com.zhang.itokenservicesso.service.consumer;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: 张宏运
 * @Date: 2019-05-20 21:31
 */
@Component
public class RedisServiceFallbackFactory implements FallbackFactory<RedisService> {
    @Override
    public RedisService create(Throwable throwable) {
        return new RedisService() {
            @Override
            public String set(String key, String value) {
                System.out.println("set Fallback了");
                return null;
            }

            @Override
            public String get(String key) {
                System.out.println("get Fallback了");
                return null;
            }
        };
    }
}
