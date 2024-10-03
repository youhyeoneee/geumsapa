package com.yhkim.domain.auth.filter;

import com.yhkim.domain.auth.grpc.client.GrpcAuthClient;
import com.yhkim.grpc.auth.AuthProto;
import com.yhkim.util.TokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final GrpcAuthClient grpcAuthClient;
    
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = TokenUtils.resolveToken(request);
        logger.info("token : " + token);
        if (token != null) {
            try {
                // gRPC를 이용한 토큰 검증
                AuthProto.ValidateTokenResponse validateTokenResponse = grpcAuthClient.validate(token);
                if (validateTokenResponse.getIsValid()) {
                    SecurityContext context = SecurityContextHolder.createEmptyContext();
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            validateTokenResponse.getUserId(), null, Collections.emptyList());
                    
                    context.setAuthentication(authentication);
                    SecurityContextHolder.setContext(context);
                }
            } catch (Exception e) {
                logger.error("Token validation failed", e);
            }
        }
        
        filterChain.doFilter(request, response);
    }
}
