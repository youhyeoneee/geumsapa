package com.yhkim.grpc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ValidateTokenResponse {
    @JsonProperty("is_valid")
    Boolean isValid;
    @JsonProperty("user_id")
    Integer userId;
}
