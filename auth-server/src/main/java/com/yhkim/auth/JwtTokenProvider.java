package com.yhkim.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    
    private final UserDetailsService userDetailsService;
    private final long tokenValidTime = 24 * 60 * 60 * 1000L; // 24 hours
    private static final String TOKEN_PREFIX = "Bearer ";
    
    @Value("${jwt.secret}")
    private String SECRET_KEY;
    private Key key;
    
    /**
     * 시크릿 키 초기화
     */
    @PostConstruct
    protected void init() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }
    
    /**
     * 토큰 생성
     *
     * @param username
     * @return
     */
    public String generateToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);
        Date now = new Date();
        log.info("createToken - username : " + username);
        
        // header에 들어갈 내용 및 서명을 하기 위한 SECRET_KEY
        // payload에 들어갈 내용
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(key)
                .compact();
    }
    
    /**
     * HTTP 요청 헤더에서 JWT 토큰을 추출
     *
     * @param request
     * @return
     */
    public String resolveToken(HttpServletRequest request) {
        
        String bearerToken = request.getHeader("Authorization");
        log.info("token : {}", bearerToken);
        
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
    
    /**
     * JWT 토큰을 복호화하여 인증 정보를 가져옴
     *
     * @param accessToken
     * @return
     */
    
    public Authentication getAuthentication(String accessToken) {
        String username = parseUsername(accessToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        
        return new UsernamePasswordAuthenticationToken(userDetails, "",
                userDetails.getAuthorities());
    }
    
    /**
     * access token에서 username 추출
     *
     * @param accessToken
     * @return
     */
    private String parseUsername(String accessToken) {
        // TODO: ExpiredJwtException GlobalExceptionHandler 추가
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(accessToken).getBody().getSubject();
    }
    
}
