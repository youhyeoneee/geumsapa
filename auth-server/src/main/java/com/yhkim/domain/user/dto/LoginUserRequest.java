package com.yhkim.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LoginUserRequest {
    @NotBlank(message = "Username is required.")
    private String username;
    
    @NotBlank(message = "Password is required.")
    private String password;
}
