package com.codingshen.demo.starter.aop;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


/**
 * TraceId拦截器
 * 将SkyWalking的TraceId添加到HTTP响应头中
 */
@Component
public class TraceIdInterceptor implements HandlerInterceptor {

    private static final String TRACE_ID_HEADER = "X-Trace-Id";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 获取SkyWalking的TraceId
        String traceId = TraceContext.traceId();
        // 添加到响应头
        response.setHeader(TRACE_ID_HEADER, traceId);
        return true;
    }
}
