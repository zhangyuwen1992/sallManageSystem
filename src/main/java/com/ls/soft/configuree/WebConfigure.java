package com.ls.soft.configuree;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;


@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableScheduling
@ComponentScan(basePackages = {"com.*"})
public class WebConfigure extends WebContextConfig {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义监听器
    }

}

