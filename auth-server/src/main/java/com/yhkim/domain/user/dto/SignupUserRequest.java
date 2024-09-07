package com.yhkim.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yhkim.domain.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@Getter
public class SignupUserRequest {
    @NotBlank(message = "Username is required.")
    @Length(min = 5, max = 20, message = "Username must be 5 to 20 characters long.")
    @Pattern(regexp = "^[a-z0-9_-]+$",
            message = "Username can only contain lowercase letters, numbers, hyphens(-), and underscores(_).")
    private String username;
    
    @NotBlank(message = "Password is required.")
    @Length(min = 8, max = 16, message = "Password must be 8 to 16 characters long.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]+$",
            message = "Password must include at least one letter, one number, and one special character (@$!%*#?&).")
    private String password;
    
    @JsonProperty("fullname")
    @NotBlank(message = "Full name is required.")
    @Length(max = 50, message = "Full name must be 50 characters or less.")
    private String fullName;
    
    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number is required.")
    @Pattern(regexp = "^01[016789]-\\d{4}-\\d{4}$",
            message = "Phone number must be in the format 010-1234-5678, 011-1234-5678, etc.")
    private String phoneNumber;
    
    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .fullName(fullName)
                .phoneNumber(phoneNumber)
                .build();
    }
}