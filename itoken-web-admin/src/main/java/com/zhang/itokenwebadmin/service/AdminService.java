package com.zhang.itokenwebadmin.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: 张宏运
 * @Date: 2019-05-15 17:34
 */
@FeignClient(value = "itoken-service-admin")
public interface AdminService {
    @GetMapping(value = "login")
    String login(@RequestParam(value = "loginCode") String loginCode,@RequestParam(value = "password") String password);
}
