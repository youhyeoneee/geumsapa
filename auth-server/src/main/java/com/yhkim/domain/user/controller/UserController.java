package com.yhkim.domain.user.controller;


import com.yhkim.domain.user.dto.SignupUserRequest;
import com.yhkim.domain.user.dto.SignupUserResponse;
import com.yhkim.domain.user.service.UserService;
import com.yhkim.util.ApiUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.yhkim.util.ApiUtils.success;

@RestController
@RequestMapping("/api/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiUtils.SuccessResponse<SignupUserResponse>> signup(@RequestBody SignupUserRequest signupUserRequest) {
        return success(HttpStatus.CREATED, "Success to signup", userService.signup(signupUserRequest));
    }
}
