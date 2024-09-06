package com.yhkim.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	USERNAME_CONFLICT(HttpStatus.CONFLICT, "E409001", "이미 사용 중인 아이디입니다.");
	
	private final HttpStatus httpStatus;
	private final String errorCode;
	private final String message;

}
