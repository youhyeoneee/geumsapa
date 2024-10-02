package com.yhkim.domain.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ValidateTokenRequest {
    String token;
}
