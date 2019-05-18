package com.zhang.itokenserviceredis.service.impl;

import com.zhang.itokenserviceredis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 张宏运
 * @Date: 2019-05-18 9:48
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void set(String key, Object value, long seconds) {
        redisTemplate.opsForValue().set(key,value,seconds, TimeUnit.SECONDS);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
