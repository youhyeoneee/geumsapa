package com.yhkim.domain.user.controller;


import com.yhkim.domain.user.dto.SignupUserRequest;
import com.yhkim.domain.user.dto.SignupUserResponse;
import com.yhkim.domain.user.service.UserService;
import com.yhkim.util.ApiUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    
    /**
     * 회원 가입
     *
     * @param signupUserRequest 가입할 회원 정보
     * @return 상태 코드 201 CREATED
     * @throws MethodArgumentNotValidException 회원가입 필드가 형식에 안맞을 경우 발생
     */
    @PostMapping
    public ResponseEntity<ApiUtils.SuccessResponse<SignupUserResponse>> signup(@Valid @RequestBody SignupUserRequest signupUserRequest) {
        return success(HttpStatus.CREATED, "Success to signup", userService.signup(signupUserRequest));
    }
}
