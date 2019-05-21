package com.zhang.itokenserviceredis.service;

/**
 * @Author: 张宏运
 * @Date: 2019-05-18 9:47
 */
public interface RedisService {
    String  set(String key, String value);
    String  get(String key);
}
