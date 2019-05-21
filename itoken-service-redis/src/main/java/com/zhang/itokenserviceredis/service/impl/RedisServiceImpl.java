package com.zhang.itokenserviceredis.service.impl;

import com.zhang.itokenserviceredis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


/**
 * @Author: 张宏运
 * @Date: 2019-05-18 9:48
 */
@Service("RedisService01")
public class RedisServiceImpl implements RedisService {
    @Autowired
    StringRedisTemplate template;

    @Override
    public String set(String key, String value) {
        template.opsForValue().set(key, value);
        return "set_is_ok";
    }

    @Override
    public String get(String key) {
        return template.opsForValue().get(key);
    }
}
