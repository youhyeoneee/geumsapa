package com.yhkim.domain.auth.controller;


import com.yhkim.domain.auth.dto.ReissueTokenResponse;
import com.yhkim.domain.auth.service.AuthService;
import com.yhkim.util.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.yhkim.util.ApiUtils.success;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    
    @GetMapping("/token/reissue")
    public ResponseEntity<ApiUtils.SuccessResponse<ReissueTokenResponse>> reissue(@RequestHeader(name = "Refresh-Token") String refreshToken) {
        return success(HttpStatus.OK, "Success to reissue access token.", authService.refresh(refreshToken));
    }
}
