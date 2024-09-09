package com.yhkim.domain.auth.service;

import com.yhkim.domain.auth.JwtTokenProvider;
import com.yhkim.domain.auth.TokenType;
import com.yhkim.domain.auth.dto.JwtTokenInfo;
import com.yhkim.domain.auth.dto.ReissueTokenResponse;
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
        // userName 가져옴
        try {
            String username = jwtTokenProvider.parseUsername(refreshToken);
            log.info("username : {}", username);
            
            // refresh Token이 DB에 없다면
            refreshTokenRepository.findByUsername(username);
            JwtTokenInfo newAccessToken = jwtTokenProvider.generateToken(username, TokenType.ACCESS_TOKEN);
            
            return ReissueTokenResponse.builder().accessToken(newAccessToken).build();
            
        } catch (ExpiredJwtException exception) {
            throw new CustomException(ErrorCode.REFRESH_TOKEN_EXPIRED);
        }
    }
    
    
}
