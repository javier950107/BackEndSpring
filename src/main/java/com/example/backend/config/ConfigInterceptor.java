package com.example.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.backend.interceptor.CustomInterceptor;

@EnableWebMvc
@Configuration
public class ConfigInterceptor implements WebMvcConfigurer{
    
    @Autowired
    private CustomInterceptor customInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(customInterceptor);
        //addPathPatterns("/users/register");
        //addPathPatterns("/api/custom/**")
    }
}
