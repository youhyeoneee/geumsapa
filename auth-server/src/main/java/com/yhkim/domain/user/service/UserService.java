package com.yhkim.domain.user.service;

import com.yhkim.domain.user.dto.*;

public interface UserService {
    
    SignupUserResponse signup(SignupUserRequest signupUserRequest);
    
    LoginUserResponse login(LoginUserRequest loginUserRequest);
    
    UserDetailResponse getUserDetail(String username);
    
    void update(String username, SignupUserRequest signupUserRequest);
    
    DeleteUserResponse delete(String username);
}
