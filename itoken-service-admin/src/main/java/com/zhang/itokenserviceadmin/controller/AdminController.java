package com.zhang.itokenserviceadmin.controller;

import com.google.common.collect.Lists;
import com.zhang.itoken.commons.BaseResult;
import com.zhang.itokencommondomain.domain.TbSysUser;
import com.zhang.itokenserviceadmin.service.AdminService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: 张宏运
 * @Date: 2019-05-15 10:36
 */
@RestController
public class AdminController {
    @Autowired
    AdminService service;

    /**
     * 登录
     */
    @GetMapping("login")
    public BaseResult login(String loginCode,String password){
        //检查
        BaseResult baseResult = checkLogin(loginCode,password);
        if (baseResult != null) {
            return baseResult;
        }


        //登录业务
        TbSysUser tbSysUser = service.login(loginCode, password);
        //登录成功
        if (tbSysUser != null) {
            return BaseResult.ok(tbSysUser);
        }
        //登录失败
        else{
            return BaseResult.notOk(Lists.newArrayList(new BaseResult.Error("","登录失败")));
        }
    }


    /**
     * @Author 张宏运
     * @Date  2019-05-15 15:59
     * 检查登录
     */
    private BaseResult checkLogin(String loginCode,String password){
        BaseResult baseResult = null;

        //这里使用Google的Guava
       /* List<BaseResult.Error> errors = Lists.newArrayList();
        if (StringUtils.isBlank(loginCode)){
            BaseResult.Error error = new BaseResult.Error();
            error.setField("loginCode");
            error.setMessage("登录账户不能为空");
            errors.add(error);
        }

        if (StringUtils.isBlank(password)){
            BaseResult.Error error = new BaseResult.Error();
            error.setField("password");
            error.setMessage("密码不能为空");
            errors.add(error);
        }*/
        if (StringUtils.isBlank(loginCode)||StringUtils.isBlank(password)){
           baseResult = BaseResult.notOk(Lists.newArrayList(
                   new BaseResult.Error("loginCode","登录账户不能为空"),
                   new BaseResult.Error("password","密码不能为空")
           ));
        }
        return baseResult;
    }
}
