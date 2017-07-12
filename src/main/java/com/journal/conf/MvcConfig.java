package com.journal.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by fernandosalazar on 1/16/17.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter{
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/home").setViewName("home");
//        registry.addViewController("/").setViewName("home");
//        registry.addViewController("/admin").setViewName("admin");
//        registry.addViewController("/journal").setViewName("journal");
        registry.addViewController("/error").setViewName("error");
        registry.addViewController("/login").setViewName("login");
    }
}
