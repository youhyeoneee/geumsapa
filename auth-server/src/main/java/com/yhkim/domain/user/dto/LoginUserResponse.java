package com.yhkim.domain.user.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.yhkim.auth.dto.JwtTokenInfo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginUserResponse {
    
    @JsonProperty("access_token")
    private JwtTokenInfo accessToken;
    
    @JsonProperty("refresh_token")
    private JwtTokenInfo refreshToken;
}
