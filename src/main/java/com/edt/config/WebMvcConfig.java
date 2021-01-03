package com.edt.config;

import com.edt.web.interceptor.LoginInterceptor;
import com.edt.web.interceptor.PermissionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public static final String[] excludePathPatterns ={
            "/css/**","/js/**","/login.html","/index","/systemDictionaryItem/selectByTypeSn",
            "/appointment/save","/userLogin","/img/**","/pageBoard","/systemDictionaryItem/selectByParentId","/messageBoard/saveOrUpdate",
            "/listBoard"
    };

    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private PermissionInterceptor permissionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePathPatterns);
        registry.addInterceptor(permissionInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePathPatterns);

    }
}
