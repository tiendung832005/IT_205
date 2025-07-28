package com.data.session15.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register", "/api/auth/**", "/api/products/**").permitAll()
                        .requestMatchers("/api/products1").permitAll()
                        .requestMatchers("/api/orders/my").hasRole("CUSTOMER")
                        .requestMatchers("/api/orders").hasAnyRole("STAFF", "ADMIN")
                        .requestMatchers("/api/orders/{id}/status").hasRole("STAFF")
                        .requestMatchers("/api/reviews/**").authenticated()
                        .requestMatchers("/api/reports/revenue").hasRole("ADMIN")
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}