package com.yhkim.domain.user.dto;


import com.yhkim.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignupUserResponse {
    private Integer userId;

    public static SignupUserResponse fromEntity(User user) {
        return SignupUserResponse.builder()
                .userId(user.getId())
                .build();
    }
}
