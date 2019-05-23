package com.zhang.itokenwebadmin.intercepter;

import com.alibaba.fastjson.JSON;
import com.zhang.itoken.commons.utils.CookieUtils;
import com.zhang.itokencommondomain.domain.TbSysUser;
import com.zhang.itokenwebadmin.service.consumer.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: 张宏运
 * @Date: 2019-05-21 16:49
 */
public class WebAdminIntercepter implements HandlerInterceptor {
    /*
     * 注意点：不能直接在拦截器里使用@Autowired 需要先在WebMvc配置里，添加拦截器的Bean
     */

    @Autowired
    RedisService redisService;

    @Value("${myUrl}")
    String url;

    @Value("${ssoUrl}")
    String ssoUrl;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        //获取url
        url = url +request.getContextPath();


        String token = CookieUtils.getCookieValue(request, "token");

        if (StringUtils.isBlank(token)){
            //token为空
            //本系统没登录 重定向到sso登录页面，带上自己的url地址作为参数，登录成功后，返回
            response.sendRedirect(ssoUrl+"?url="+url);
            //false 是禁行，在此处被拦截
            return false;
        }else{
            //token不为空

            //true 是放行 进入拦截器的下一个阶段 需要验证token
            //如果有token的cookie ，需要进行下一步的验证 进入postHandle
            return true;
        }


    }


    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
       /**
        * 验证token
        */


        //request.getAttribute("returnUrl");

        //局部会话
        HttpSession session = request.getSession();
        TbSysUser tbSysUser = (TbSysUser) session.getAttribute("tbSysUser");
        String token = (String) session.getAttribute("token");
        if (token != null) {
            CookieUtils.setCookie(request,response,"token",token);
        }

        //如果tbSysUser不为空，说明已经通过本系统登录 直接放行
        if (tbSysUser != null) {
            if (modelAndView != null) {
                modelAndView.addObject("tbSysUser",tbSysUser);
            }
        }else{
            //tbSysUser == null 说明在本系统没有登录，但需要确定在其他系统有没有登录

            token = CookieUtils.getCookieValue(request,"token");
            if (StringUtils.isNotBlank(token)){
                String loginCode = redisService.get(token);
                if(StringUtils.isNotBlank(loginCode)){
                    String json = redisService.get(loginCode);
                    if(StringUtils.isNotBlank(json)){
                        //已登录状态 ,创建局部会话
                        tbSysUser = JSON.parseObject(json,TbSysUser.class);
                        if (modelAndView != null) {
                            modelAndView.addObject("tbSysUser",tbSysUser);
                        }
                        request.getSession().setAttribute("tbSysUser",tbSysUser);

                    }
                }
            }


        }
        //再次确认
        if (tbSysUser == null) {
            response.sendRedirect(ssoUrl+"?url="+url);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
