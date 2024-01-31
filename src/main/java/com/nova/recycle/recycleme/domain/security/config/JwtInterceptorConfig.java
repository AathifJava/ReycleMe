package com.nova.recycle.recycleme.domain.security.config;


import com.nova.recycle.recycleme.domain.security.RequestMetaDTO;
import com.nova.recycle.recycleme.domain.security.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class JwtInterceptorConfig implements HandlerInterceptor {

    @Autowired
    JwtUtil jwtUtil;

    RequestMetaDTO requestMetaDTO;

    public JwtInterceptorConfig(RequestMetaDTO requestMetaDTO){
        this.requestMetaDTO = requestMetaDTO;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String authorization = request.getHeader("authorization");
        if (!(request.getRequestURI().contains("auth/"))){
            Claims claims = jwtUtil.verifyToken(authorization);
            requestMetaDTO.setId(Long.valueOf(claims.getIssuer()));
            requestMetaDTO.setMobile(claims.get("mobile").toString());
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);

    }
}
