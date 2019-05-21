package com.zhang.itokencommonweb.intercepter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 张宏运
 * @Date: 2019-05-21 16:00
 * 初始化常量拦截器
 * 可以将一些页面用到的常量放在这里
 * 下面是例子
 * 可以把常量放到model,避免重复在页面写大量CDN地址之类的
 */
public class ConstantsIntercepter implements HandlerInterceptor {

    private  static  String CDN_HOST = "netdna.bootstrapcdn.com";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if (modelAndView != null) {
            modelAndView.addObject("CDN",CDN_HOST);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
