package com.yhkim.grpc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ValidateTokenResponse {
    Boolean validated;
    @JsonProperty("user_id")
    Integer userId;
}
