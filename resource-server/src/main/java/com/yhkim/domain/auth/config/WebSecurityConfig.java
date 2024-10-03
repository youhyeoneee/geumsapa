package com.yhkim.domain.auth.config;


import com.yhkim.domain.auth.filter.JwtAuthenticationFilter;
import com.yhkim.domain.auth.grpc.client.GrpcAuthClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
    
    private final GrpcAuthClient authClient;
    
    public WebSecurityConfig(GrpcAuthClient authClient) {
        this.authClient = authClient;
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        http
                .csrf((AbstractHttpConfigurer::disable))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/h2-console/**", "/swagger-ui/**").permitAll()
                        
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtAuthenticationFilter(authClient), UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
}
