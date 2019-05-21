package com.zhang.itokenservicesso.controller;

import com.alibaba.fastjson.JSON;
import com.zhang.itokencommondomain.domain.TbSysUser;
import com.zhang.itokenservicesso.service.LoginService;
import com.zhang.itokenservicesso.service.consumer.RedisService;
import com.zhang.itokenservicesso.utils.CookieUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Author: 张宏运
 * @Date: 2019-05-20 20:10
 */
@Controller
public class LoginController {

    @Autowired
    LoginService loginService;
    @Autowired
    RedisService redisService;
    /**
     * 跳转登录页
     */

    @GetMapping(value={"login",""})
    public String login(@RequestParam(required = false) String url,
                        HttpServletRequest request,
                        Model model){

        String token = CookieUtils.getCookieValue(request,"token");
        //token 不为空，有可能登录，但需要去redis进一步判断
        if (StringUtils.isNotBlank(token)) {

            String loginCode = redisService.get(token);
            if (StringUtils.isNotBlank(loginCode)) {

                String json = redisService.get(loginCode);
                TbSysUser tbSysUser = JSON.parseObject(json, TbSysUser.class);
               if(StringUtils.isNotBlank(url)){
                   return "redirect:"+url;
               }
               model.addAttribute("tbSysUser",tbSysUser);
               model.addAttribute("msg","您已登录");
            }
        }
        if (StringUtils.isNotBlank(url)){
            model.addAttribute("url",url);
        }
        return "login";
    }



    /**
     *登录业务
     */
    @PostMapping("login")
    public String login(@RequestParam("loginCode") String loginCode,
                        @RequestParam("password") String password,
                        @RequestParam(required = false) String url,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        Model model,
                        RedirectAttributes redirectAttributes
    ){
        TbSysUser tbSysUser = loginService.login(loginCode,password);
        if (tbSysUser == null) {
            //登录失败
            //model.addAttribute("msg","登录失败，用户名或密码错误");
            redirectAttributes.addFlashAttribute("msg","登录失败，用户名或密码错误");
        }else{
            /*登录成功
                1 存入缓存，获得result
                2 存入缓存成功，存入cookie，返回
                timeOut 为过期时间
             */

            String token = UUID.randomUUID().toString();
            String result = redisService.set(token,loginCode);
            if ("ok".equals(result)) {
                CookieUtils.setCookie(request,response,"token",token,24*60*60);
                if (StringUtils.isNotBlank(url)){
                    return "redirecit:"+url;
                }

            }else {
                //熔断处理
                //model.addAttribute("msg","服务器异常，请稍后访问");
                redirectAttributes.addFlashAttribute("msg","服务器异常，请稍后访问");

            }

        }
        //登录成功，重定向到登录页
        return "redirect:/login";
    }

}
