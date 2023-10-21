package com.JEEProject.TableStore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/Js/");
        registry.addResourceHandler("/js/adminProducts").addResourceLocations("/WEB-INF/Js/adminProducts");
        registry.addResourceHandler("/js/adminCategory").addResourceLocations("/WEB-INF/Js/adminCategory");
        registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/images/");
        registry.addResourceHandler("/images/products/**").addResourceLocations("/WEB-INF/images/products/");
    }
}
