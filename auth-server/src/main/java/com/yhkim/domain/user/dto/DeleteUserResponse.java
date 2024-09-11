package com.yhkim.domain.user.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class DeleteUserResponse {
    @JsonProperty("user_id")
    private Integer userId;
    
    @JsonProperty("deleted_at")
    private LocalDateTime deletedAt;
}
