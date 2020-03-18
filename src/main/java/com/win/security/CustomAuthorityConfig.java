package com.win.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class CustomAuthorityConfig extends ResourceServerConfigurerAdapter {
    
    @Override
    public void configure(HttpSecurity pHttp) throws Exception {
        
        pHttp.authorizeRequests().antMatchers("/catalog/**").hasAuthority("resource:read")
        .antMatchers("/").permitAll();
    }
} 