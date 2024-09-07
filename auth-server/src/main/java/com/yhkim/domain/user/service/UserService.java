package com.yhkim.domain.user.service;

import com.yhkim.domain.user.dto.SignupUserRequest;
import com.yhkim.domain.user.dto.SignupUserResponse;

public interface UserService {

    SignupUserResponse signup(SignupUserRequest signupUserRequest);
}
