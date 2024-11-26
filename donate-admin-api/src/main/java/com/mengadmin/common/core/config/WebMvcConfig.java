package com.mengadmin.common.core.config;

import com.mengadmin.common.core.Constants;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvc配置, 拦截器、资源映射等都在此配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 支持跨域访问
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedHeaders("*")
                .exposedHeaders(Constants.TOKEN_HEADER_NAME)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                .allowCredentials(true)
                .maxAge(3600);
    }

    /**
     * 静态资源映射
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 获取操作系统
        String os = System.getProperty("os.name");
        // 判断
        if (os.toLowerCase().startsWith("win")) {
            // 如果是Windows系统
            registry.addResourceHandler("/api/**").addResourceLocations("file:/c:/upload/");
        } else { // mac
            registry.addResourceHandler("/api/**")
                    .addResourceLocations("file:/Users/yaoyaomice/uploadFile/");
        }
    }

}
