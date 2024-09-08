package com.yhkim.domain.user.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.yhkim.auth.dto.JwtTokenInfo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginUserResponse {
    
    @JsonProperty("access_token")
    private String accessToken;
    
    @JsonProperty("expired_at")
    private long expiredAt;
    
    public static LoginUserResponse fromJwtTokenInfo(JwtTokenInfo jwtTokenInfo) {
        return LoginUserResponse.builder()
                .accessToken(jwtTokenInfo.getAccessToken())
                .expiredAt(jwtTokenInfo.getExpiresIn())
                .build();
    }
}
