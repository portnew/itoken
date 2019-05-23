package com.zhang.itokenwebadmin.controller;


import com.zhang.itokenwebadmin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 张宏运
 * @Date: 2019-05-15 17:19
 */
@Controller
public class AdminController {
    @Autowired
    AdminService service;

    @GetMapping(value = {"index",""})
    public String index(){
        System.out.println("来到了Admin的controller index方法");
        return "index";
    }
}
