package com.doan.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.doan.shop.interceptor.CheckAdminInterceptor;
import com.doan.shop.interceptor.LoginInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    @Bean
    public CheckAdminInterceptor checkAdminInterceptor() {
        return new CheckAdminInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor())
            .addPathPatterns("/cart/**", "/checkout/**", "/order/**");
        registry.addInterceptor(checkAdminInterceptor())
            .addPathPatterns("/admin/**");
    }
}