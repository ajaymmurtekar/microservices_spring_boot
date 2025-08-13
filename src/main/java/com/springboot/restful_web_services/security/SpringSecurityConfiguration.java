package com.springboot.restful_web_services.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //All requests must be authenticated
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );

        //If request is not authenticated, it will redirect to login page
        http.httpBasic(Customizer.withDefaults());
        //disable CSRF for -> POST, PUT, DELETE, PATCH
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
