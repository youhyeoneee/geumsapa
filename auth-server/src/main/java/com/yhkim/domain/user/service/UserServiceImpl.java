package com.yhkim.domain.user.service;


import com.yhkim.auth.JwtTokenProvider;
import com.yhkim.auth.dto.JwtTokenInfo;
import com.yhkim.domain.user.dto.LoginUserRequest;
import com.yhkim.domain.user.dto.LoginUserResponse;
import com.yhkim.domain.user.dto.SignupUserRequest;
import com.yhkim.domain.user.dto.SignupUserResponse;
import com.yhkim.domain.user.entity.User;
import com.yhkim.domain.user.repository.UserRepository;
import com.yhkim.exception.CustomException;
import com.yhkim.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    
    
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
    public LoginUserResponse login(LoginUserRequest loginUserRequest) {
        User user = userRepository.findByUsername(loginUserRequest.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.USERNAME_NOT_FOUND));
        
        if (!passwordEncoder.matches(loginUserRequest.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }
        
        JwtTokenInfo jwtTokenInfo = jwtTokenProvider.generateToken(user.getUsername());
        return LoginUserResponse.fromJwtTokenInfo(jwtTokenInfo);
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
