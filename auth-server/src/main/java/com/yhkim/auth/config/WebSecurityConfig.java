package com.yhkim.auth.config;

import com.yhkim.auth.JwtTokenProvider;
import com.yhkim.auth.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
    
    @Bean
    public SecureRandom secureRandom() {
        
        return new SecureRandom();
    }
    
    private final JwtTokenProvider jwtTokenProvider;
    
    public WebSecurityConfig(JwtTokenProvider jwtTokenProvider) {
        
        this.jwtTokenProvider = jwtTokenProvider;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        
        return (web) -> web.ignoring().requestMatchers("/h2-console/**"
                , "/favicon.ico"
                , "/error");
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        http
                .csrf((AbstractHttpConfigurer::disable))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/users/signup", "/api/users/login", "/api/auth/token", "/h2-console/**", "/swagger-ui/**").permitAll()
                        
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
    
}
