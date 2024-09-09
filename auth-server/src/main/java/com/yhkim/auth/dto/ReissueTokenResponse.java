package com.yhkim.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class ReissueTokenResponse {
    
    @JsonProperty("access_token")
    JwtTokenInfo accessToken;
}
