package com.springboot.adminmanage.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * author: ouyang
 * Date:2020/2/2 12:34
 **/
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath/static/");
        super.addResourceHandlers(registry);
    }
}
