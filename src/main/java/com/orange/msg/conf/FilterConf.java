package com.orange.msg.conf;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FilterConf {

//    @Bean
    public FilterRegistrationBean indexFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(OAuthFilter());
        registration.addUrlPatterns("/business/*");
        registration.addUrlPatterns("/notice/*");
        registration.addUrlPatterns("/receive_message/*");
        return registration;
    }

    @Bean
    public Filter OAuthFilter(){
        return new OAuthFilter();
    }
}
