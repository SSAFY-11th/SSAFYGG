package com.ssafy.altf4.global.config;

import com.ssafy.altf4.global.jwt.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${token.access-token-expiration-time}")
    private String accessTokenExpirationTime;
    @Value("${token.refresh-token-expiration-time}")
    private String refreshTokenExpirationTime;
    @Value("${token.secret}")
    private String tokenSecret;

    @Bean
    public TokenManager tokenManager(){
        return new TokenManager(accessTokenExpirationTime, refreshTokenExpirationTime, tokenSecret);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration config = new CorsConfiguration(); // CORS 요청 처리 설정 객체 생성
            config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization")); // 허용할 Http Header 지정
            config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE")); // 허용할 HTTP Method 지
            config.setAllowedOriginPatterns(Collections.singletonList("http://localhost:3000")); // 허용할 origin 지정
            config.setAllowCredentials(true);
            config.setMaxAge(3600L); // 사전 요청(pre-flight request)의 결과를 캐시하는 시간을 초 단위로 설정합니다.
            return config;
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .httpBasic(AbstractHttpConfigurer::disable) // 기본 HTTP 인증 활성화/비활성화
                .csrf(AbstractHttpConfigurer::disable) // CSRF(Cross-Site Request Forgery) 보호 기능 활성화/비활성화
                .cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource())) // cors 설정 활성 (위에 정의한 메서드 활용)
//                .formLogin(login -> login
//                        .loginPage("/login") // 사용자 정의 로그인 페이지
//                        .permitAll() // 모두에게 로그인 페이지 접근 허용
//                )
                .logout(logout -> logout.permitAll()) // 로그아웃 모두에게 허용
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                ) // 특정 경로에 대한 접근 권한 설정
                .build();
    }
}
