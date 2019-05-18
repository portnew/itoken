package com.zhang.itokenserviceredis.service;

/**
 * @Author: 张宏运
 * @Date: 2019-05-18 9:47
 */
public interface RedisService {
    void set(String key, Object value, long seconds);
    Object get(String key);
}
