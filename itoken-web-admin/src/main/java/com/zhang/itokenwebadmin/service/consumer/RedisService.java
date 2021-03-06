package com.zhang.itokenwebadmin.service.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: 张宏运
 * @Date: 2019-05-20 19:28
 */
@FeignClient(value = "itoken-service-redis",fallbackFactory = RedisServiceFallbackFactory.class)
public interface RedisService {
    @RequestMapping(value = "set",method = RequestMethod.POST)
    String set(@RequestParam String key, @RequestParam String value);

    @RequestMapping(value = "get",method = RequestMethod.GET)
    String get(@RequestParam String key);
}
