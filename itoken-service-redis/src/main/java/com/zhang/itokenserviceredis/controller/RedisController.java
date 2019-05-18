package com.zhang.itokenserviceredis.controller;

import com.zhang.itokenserviceredis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @Author: 张宏运
 * @Date: 2019-05-18 9:53
 */
@RestController
public class RedisController {
    @Autowired
    RedisService service;

    @PostMapping("put")
    public String set(String key, Object value, long seconds){
        service.set(key,value,seconds);
        return "ok";
    }
    @GetMapping("get")
    public Object get(String key){
        String json = null;
        Object o = service.get(key);
        if (o != null) {
            return json = (String) o;
        }
        return "not_ok";
    }
}
