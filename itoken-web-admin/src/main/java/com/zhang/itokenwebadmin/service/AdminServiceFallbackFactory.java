package com.zhang.itokenwebadmin.service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.zhang.itoken.commons.BaseResult;
import com.zhang.itokenwebadmin.service.AdminService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: 张宏运
 * @Date: 2019-05-16 9:47
 */
@Component
public class AdminServiceFallbackFactory implements FallbackFactory<AdminService> {
    @Override
    public AdminService create(Throwable throwable) {
        return new AdminService() {
            @Override
            public String login(String loginCode, String password) {
                BaseResult result = BaseResult.notOk(Lists.newArrayList(new BaseResult.Error("502","Bad GateWy")));
                System.out.println("Fallback打印：000000000000"+result.toString());
                try{

                    String str = (String) JSONObject.toJSON(result);
                    System.out.println(str);
                    return str;
                }catch (Exception e){
                    e.getStackTrace();
                }
                return null;
            }


        };
    }
}
