package com.yhkim.grpc.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ValidateTokenRequest {
    String token;
}
