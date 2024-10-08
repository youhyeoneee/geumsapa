package com.yhkim.domain.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yhkim.domain.auth.JwtTokenProvider;
import com.yhkim.exception.ErrorCode;
import com.yhkim.util.TokenUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
public class
JwtAuthenticationFilter extends GenericFilterBean {
    
    private final JwtTokenProvider jwtTokenProvider;
    
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String requestURI = httpRequest.getRequestURI();
            
            String token = TokenUtils.resolveToken(httpRequest);
            
            if (token != null) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            
            chain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            logger.error("만료된 JWT 토큰입니다.");
            jwtExceptionHandler((HttpServletResponse) response, ErrorCode.ACCESS_TOKEN_EXPIRED);
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            // TODO: Error code 생성 및 처리
            logger.error("잘못된 JWT 서명입니다.");
        } catch (UnsupportedJwtException e) {
            // TODO: Error code 생성 및 처리
            logger.error("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            // TODO: Error code 생성 및 처리
            logger.error("JWT 토큰이 잘못되었습니다.");
        }
    }
    
    // 토큰에 대한 오류가 발생했을 때, 커스터마이징해서 Exception 처리 값을 클라이언트에게 알려준다.
    public void jwtExceptionHandler(HttpServletResponse response, ErrorCode errorCode) {
        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(Map.of(
                    "success", false,
                    "message", errorCode.getMessage(),
                    "http_status", errorCode.getHttpStatus().value(),
                    "error_code", errorCode.getErrorCode()
            ));
            response.getWriter().write(json);
        } catch (Exception e) {
            log.error("Error writing JSON response", e);
        }
    }
}
