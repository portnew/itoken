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
import javax.servlet.http.HttpSession;
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
     * 跳转到登录页
     * method:GET
     * 对是否登录进行预判断
     * 如果已经登录，直接返回
     * 如果未登录，转到登录页
     */

    @GetMapping(value = {"login", ""})
    public String login(@RequestParam(required = false) String url,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        Model model) {

        String token = CookieUtils.getCookieValue(request, "token");

        //如果 token 不为空，有可能登录，但需要去redis进一步判断

        if (StringUtils.isNotBlank(token)) {

            String loginCode = redisService.get(token);
            if (StringUtils.isNotBlank(loginCode)) {

                String json = redisService.get(loginCode);
                TbSysUser tbSysUser = JSON.parseObject(json, TbSysUser.class);

                model.addAttribute("tbSysUser", tbSysUser);
                model.addAttribute("msg", "您已登录");

                //把token返回给请求方
                CookieUtils.setCookie(request, response, "token", token);
                if (StringUtils.isNotBlank(url)) {
                    return "redirect:" + url;
                }

            }
        }
        //没有通过登录验证，把url放入，跳转到login.html进行登录
        //login.html 进行登录，以post请求方式，进入下面的业务处理流程
        if (StringUtils.isNotBlank(url)) {
            model.addAttribute("url", url);
        }
        return "login";
    }


    /**
     * 登录业务
     */
    @PostMapping("login")
    public String login(@RequestParam("loginCode") String loginCode,
                        @RequestParam("password") String password,
                        @RequestParam(required = false) String url,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        Model model
    ) {
        TbSysUser tbSysUser = loginService.login(loginCode, password);
        if (tbSysUser == null) {
            //登录失败,要返回到登录页面
            System.out.println("登录失败");
            model.addAttribute("msg","登录失败，用户名或密码错误");
            model.addAttribute("url",url);
            return "login";
        } else {
            /*登录成功
                1 存入缓存，获得result
                2 存入缓存成功，存入cookie，返回
             */
            System.out.println("登录成功");
            //生成token,第一次登录的时候生成
            String token = UUID.randomUUID().toString();
            //将token 写入Redis
            String result = redisService.set(token, loginCode);
            if ("ok".equals(result)) {
                //把token返回给请求方
                CookieUtils.setCookie(request, response, "token", token, 24 * 60 * 60);
                //全局session,返回令牌
                HttpSession session = request.getSession();
                session.setAttribute("tbSysUser",tbSysUser);
                session.setAttribute("token",token);

                if (StringUtils.isNotBlank(url)) {
                    model.addAttribute("tbSysUser", tbSysUser);

                }

            } else {
                //熔断处理
                model.addAttribute("msg","服务器异常，请稍后访问");
                // redirectAttributes.addFlashAttribute("msg", "服务器异常，请稍后访问");
                model.addAttribute("url",url);
                return "login";
            }

            //登录成功，跳转回原系统
            //http://localhost:8503/?url=localhost:8602
            System.out.println(url);
            return "redirect:" + url;
        }


    }
}


