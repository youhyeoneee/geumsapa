package com.yhkim.domain.user.controller;


import com.yhkim.domain.user.dto.*;
import com.yhkim.domain.user.service.UserService;
import com.yhkim.util.ApiUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(value = "/signup")
    public ResponseEntity<ApiUtils.SuccessResponse<SignupUserResponse>> signup(@Valid @RequestBody SignupUserRequest signupUserRequest) {
        return success(HttpStatus.CREATED, "Success to signup.", userService.signup(signupUserRequest));
    }
    
    @PostMapping(value = "/login")
    public ResponseEntity<ApiUtils.SuccessResponse<LoginUserResponse>> login(@Valid @RequestBody LoginUserRequest loginUserRequest) {
        return success(HttpStatus.OK, "Success to login.", userService.login(loginUserRequest));
    }
    
    @GetMapping(value = "/me")
    public ResponseEntity<ApiUtils.SuccessResponse<UserDetailResponse>> detail(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return success(HttpStatus.OK, "Success to get user's detail.", userService.getUserDetail(username));
    }
}
