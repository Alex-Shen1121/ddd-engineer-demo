package com.codingshen.demo.starter.config;

import com.codingshen.demo.starter.aop.TraceIdInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC 配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private TraceIdInterceptor traceIdInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册TraceId拦截器，拦截所有请求
        registry.addInterceptor(traceIdInterceptor)
                .addPathPatterns("/**");
    }
}
