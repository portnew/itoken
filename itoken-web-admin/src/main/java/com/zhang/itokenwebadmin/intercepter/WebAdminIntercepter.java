package com.zhang.itokenwebadmin.intercepter;

import com.alibaba.fastjson.JSON;
import com.zhang.itoken.commons.utils.CookieUtils;
import com.zhang.itokencommondomain.domain.TbSysUser;
import com.zhang.itokenwebadmin.service.consumer.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    RedisService redisService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = CookieUtils.getCookieValue(request, "token");
        if (StringUtils.isBlank(token)){
            //肯定没登录
            response.sendRedirect("http://localhost:8503?url=http://localhost:8602");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        HttpSession session = request.getSession();
        TbSysUser tbSysUser = (TbSysUser) session.getAttribute("tbSysUser");
        if (tbSysUser != null) {
            if (modelAndView != null) {
                modelAndView.addObject("tbSysUser",tbSysUser);
            }
        }else{
            //未登录
            String token = CookieUtils.getCookieValue(request,"token");
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
            response.sendRedirect("http://localhost:8503?url=http://localhost:8602");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
