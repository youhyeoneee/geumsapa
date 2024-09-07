package com.yhkim.domain.user.service;


import com.yhkim.domain.user.dto.SignupUserRequest;
import com.yhkim.domain.user.dto.SignupUserResponse;
import com.yhkim.domain.user.entity.User;
import com.yhkim.domain.user.repository.UserRepository;
import com.yhkim.exception.CustomException;
import com.yhkim.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public SignupUserResponse signup(SignupUserRequest signupUserRequest) {
        if (isUserExist(signupUserRequest.getUsername())) {
            throw new CustomException(ErrorCode.USERNAME_ALREADY_EXIST);
        }

        User savedUser = userRepository.save(signupUserRequest.toEntity());
        return SignupUserResponse.fromEntity(savedUser);
    }

    private boolean isUserExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
