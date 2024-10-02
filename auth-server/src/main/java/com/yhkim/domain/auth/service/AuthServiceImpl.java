package com.yhkim.domain.auth.service;

import com.yhkim.domain.auth.JwtTokenProvider;
import com.yhkim.domain.auth.TokenType;
import com.yhkim.domain.auth.dto.JwtTokenInfo;
import com.yhkim.domain.auth.dto.ReissueTokenResponse;
import com.yhkim.domain.auth.entity.RefreshToken;
import com.yhkim.domain.auth.repository.RefreshTokenRepository;
import com.yhkim.exception.CustomException;
import com.yhkim.exception.ErrorCode;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    
    
    @Override
    public ReissueTokenResponse refresh(String refreshToken) {
        try {
            
            // refresh token이 아닐 경우
            String tokenType = jwtTokenProvider.parseTokenType(refreshToken);
            if (!tokenType.equals(TokenType.REFRESH_TOKEN.name())) {
                throw new CustomException(ErrorCode.INVALID_TOKEN_TYPE);
            }
            
            String username = jwtTokenProvider.parseUsername(refreshToken);
            log.info("username : {}", username);
            
            // refresh Token이 DB에 없다면
            RefreshToken obj = refreshTokenRepository.findByUsername(username).orElseThrow(() -> new CustomException(ErrorCode.REFRESH_TOKEN_NOT_FOUND));
            
            // 깉지 않을 경우
            if (!obj.getToken().equals(refreshToken)) {
                throw new CustomException(ErrorCode.REFRESH_TOKEN_MISMATCH);
            }
            
            // access token 발급
            JwtTokenInfo newAccessToken = jwtTokenProvider.generateToken(username, TokenType.ACCESS_TOKEN);
            
            return ReissueTokenResponse.builder()
                    .accessToken(newAccessToken.getToken())
                    .expiredAt(newAccessToken.getExpiresIn())
                    .build();
            
        } catch (ExpiredJwtException exception) {
            throw new CustomException(ErrorCode.REFRESH_TOKEN_EXPIRED);
        }
    }
    
    @Override
    public String validateToken(String token) {
        return jwtTokenProvider.parseUsername(token);
    }
    
    
}
