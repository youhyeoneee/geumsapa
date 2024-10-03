package com.yhkim.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;


@Slf4j
public class TokenUtils {
    private static final String TOKEN_PREFIX = "Bearer ";
    
    /**
     * HTTP 요청 헤더에서 JWT 토큰을 추출
     *
     * @param request
     * @return
     */
    public static String resolveToken(HttpServletRequest request) {
        
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("bearerToken : {}", bearerToken);
        
        return resolveTokenFromBearerToken(bearerToken);
    }
    
    /**
     * bearerToken 토큰에서 token만 추출
     *
     * @param bearerToken
     * @return
     */
    private static String resolveTokenFromBearerToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
}
