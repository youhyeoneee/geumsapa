package com.yhkim.domain.user.service;


import com.yhkim.domain.auth.JwtTokenProvider;
import com.yhkim.domain.auth.TokenType;
import com.yhkim.domain.auth.dto.JwtTokenInfo;
import com.yhkim.domain.auth.entity.RefreshToken;
import com.yhkim.domain.auth.repository.RefreshTokenRepository;
import com.yhkim.domain.user.dto.*;
import com.yhkim.domain.user.entity.User;
import com.yhkim.domain.user.repository.UserRepository;
import com.yhkim.exception.CustomException;
import com.yhkim.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    
    
    /**
     * 회원 가입 처리
     *
     * @param signupUserRequest 회원 가입 요청 정보
     * @return SignupUserResponse 가입된 사용자 정보 응답
     * @throws CustomException 계정이 이미 존재하는 경우 발생
     */
    @Override
    @Transactional
    public SignupUserResponse signup(SignupUserRequest signupUserRequest) {
        if (isUserExist(signupUserRequest.getUsername())) {
            throw new CustomException(ErrorCode.USERNAME_ALREADY_EXIST);
        }
        
        String encodedPassword = passwordEncoder.encode(signupUserRequest.getPassword());
        signupUserRequest.setPassword(encodedPassword);
        
        User savedUser = userRepository.save(signupUserRequest.toEntity());
        return SignupUserResponse.fromEntity(savedUser);
    }
    
    @Override
    @Transactional
    public LoginUserResponse login(LoginUserRequest loginUserRequest) {
        
        // username 존재하는지 확인
        User user = userRepository.findByUsername(loginUserRequest.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.USERNAME_NOT_FOUND));
        
        // 비밀번호 일치하는지 확인
        if (!passwordEncoder.matches(loginUserRequest.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }
        
        
        // access, refresh 토큰 생성
        JwtTokenInfo accessTokenInfo = jwtTokenProvider.generateToken(user.getUsername(), TokenType.ACCESS_TOKEN);
        JwtTokenInfo refreshTokenInfo = jwtTokenProvider.generateToken(user.getUsername(), TokenType.REFRESH_TOKEN);
        
        // refresh 토큰 DB에 upsert
        Optional<RefreshToken> savedRefreshToken = refreshTokenRepository.findByUsername(user.getUsername());
        
        if (savedRefreshToken.isEmpty()) {
            RefreshToken obj = RefreshToken.builder()
                    .username(user.getUsername())
                    .token(refreshTokenInfo.getToken())
                    .build();
            
            refreshTokenRepository.save(obj);
        } else {
            savedRefreshToken.get().setToken(refreshTokenInfo.getToken());
            refreshTokenRepository.save(savedRefreshToken.get());
        }
        
        return LoginUserResponse.builder()
                .accessToken(accessTokenInfo.getToken())
                .expiredAt(accessTokenInfo.getExpiresIn())
                .refreshToken(refreshTokenInfo.getToken())
                .build();
    }
    
    /**
     * 유저 정보 조회
     *
     * @param username
     * @return
     */
    @Override
    public UserDetailResponse getUserDetail(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.USERNAME_NOT_FOUND));
        return user.getUserDetail();
    }
    
    @Override
    public void update(String username, SignupUserRequest signupUserRequest) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.USERNAME_NOT_FOUND));
        
        String encodedPassword = passwordEncoder.encode(signupUserRequest.getPassword());
        
        user.update(signupUserRequest.getUsername(), encodedPassword,
                signupUserRequest.getFullName(), signupUserRequest.getPhoneNumber());
        
        userRepository.save(user);
    }
    
    /**
     * 계정 존재 여부 확인
     *
     * @param username 확인할 계정
     * @return boolean 존재 여부 (true: 존재함, false: 존재 하지 않음)
     */
    private boolean isUserExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
