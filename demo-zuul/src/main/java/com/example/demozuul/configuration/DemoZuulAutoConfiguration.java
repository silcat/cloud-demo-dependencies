package com.example.demozuul.configuration;

import com.example.demozuul.configuration.filter.ErrorFilter;
import com.example.demozuul.configuration.filter.PostFilter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.http.ZuulServlet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(ZuulServlet.class)
public class DemoZuulAutoConfiguration {

    @Bean
    public ZuulFilter errorFilter(){
        return new ErrorFilter();
    }
    @Bean
    public ZuulFilter PostFilter(){
        return new PostFilter();
    }
}
