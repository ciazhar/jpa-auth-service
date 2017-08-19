package com.ciazhar.authserver.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by ciazhar on 8/19/17.
 */


@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
    ///konfigurasi page yg gak ada controllernya tapi mau di UI
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/login").setViewName("login");
    }
}
