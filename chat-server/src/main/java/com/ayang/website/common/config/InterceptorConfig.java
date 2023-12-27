package com.ayang.website.common.config;

import com.ayang.website.common.interceptor.BlackInterceptor;
import com.ayang.website.common.interceptor.CollectorInterceptor;
import com.ayang.website.common.interceptor.TokenInterceptor;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author shy
 * @date 2023/12/19
 * @description 拦截器配置
 */
@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    public static final String CAPI = "/capi/**";
    private final TokenInterceptor tokenInterceptor;
    private final CollectorInterceptor collectorInterceptor;
    private final BlackInterceptor blackInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns(CAPI);
        registry.addInterceptor(collectorInterceptor)
                .addPathPatterns(CAPI);
        registry.addInterceptor(blackInterceptor)
                .addPathPatterns(CAPI);
    }
}
