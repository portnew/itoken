package com.zhang.itokencommonweb.iconfig;

import com.zhang.itokencommonweb.intercepter.ConstantsIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: 张宏运
 * @Date: 2019-05-21 16:13
 */
@Configuration
public class IntercepterConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ConstantsIntercepter()).addPathPatterns("/**");
    }
}
