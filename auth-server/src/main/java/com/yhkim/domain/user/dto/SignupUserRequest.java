package com.yhkim.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yhkim.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class SignupUserRequest {
	private String username;
	private String password;
	@JsonProperty("fullname")
	private String fullName;
	@JsonProperty("phone_number")
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
