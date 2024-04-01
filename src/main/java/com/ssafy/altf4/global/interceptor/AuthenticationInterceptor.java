package com.ssafy.altf4.global.interceptor;

import com.ssafy.altf4.global.jwt.TokenManager;
import com.ssafy.altf4.global.jwt.TokenType;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // preflight 요청 넘기기
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }

        String authorization = request.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        Claims claims = tokenManager.getTokenClaims(accessToken);

        String tokenType = claims.getSubject();

        if (!TokenType.isAccessToken(tokenType))
            throw new RuntimeException("Access Token이 아닙니다.");

        return true;
    }
}
