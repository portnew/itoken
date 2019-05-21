package com.zhang.itokenwebadmin.Config;

import com.zhang.itokenwebadmin.intercepter.WebAdminIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: 张宏运
 * @Date: 2019-05-21 16:49
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new WebAdminIntercepter())
                .addPathPatterns("/**")
                .excludePathPatterns("/static");
    }
}
