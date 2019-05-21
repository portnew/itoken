package com.zhang.itokenserviceredis.controller;

import com.zhang.itokenserviceredis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: 张宏运
 * @Date: 2019-05-18 9:53
 */
@RestController
public class RedisController {
    @Autowired
    RedisService service;

    @PostMapping("set")
    public String set(String key, String value){
        service.set(key,value);
        return "ok";
    }
    @GetMapping("get")
    public String get(String key){
        String json = null;
        String str = service.get(key);
        if (str != null) {
            return json = str;
        }
        return json;
    }
}
