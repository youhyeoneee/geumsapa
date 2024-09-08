package com.yhkim.auth.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtTokenInfo {
    private final String accessToken;
    private final long expiresIn;
}
