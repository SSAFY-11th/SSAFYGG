package com.ssafy.altf4.global.config;

import com.ssafy.altf4.global.interceptor.AdminAuthorizationInterceptor;
import com.ssafy.altf4.global.interceptor.AuthenticationInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    // accessToken 검증
    private final AuthenticationInterceptor authenticationInterceptor;
    // Role ADMIN 체크하는 인터셉터
    private final AdminAuthorizationInterceptor adminAuthorizationInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(authenticationInterceptor)
                .order(1)
                .addPathPatterns("/altf4/member/update");

        registry.addInterceptor(adminAuthorizationInterceptor)
                .order(2)
                .addPathPatterns("/meeti/admin/**");
    }
}
