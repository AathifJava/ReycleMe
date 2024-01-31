package com.nova.recycle.recycleme.domain.security.config;

import com.nova.recycle.recycleme.domain.security.RequestMetaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomWebConfig implements WebMvcConfigurer {
    @Autowired
    JwtInterceptorConfig jwtInterceptorConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptorConfig);
    }

    @Bean
    @RequestScope
    public RequestMetaDTO requestMetaDTO(){
        return new RequestMetaDTO();
    }

}
