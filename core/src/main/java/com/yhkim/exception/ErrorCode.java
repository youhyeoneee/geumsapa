package com.yhkim.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USERNAME_ALREADY_EXIST(HttpStatus.CONFLICT, "E409001", "Username already exists.");

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String message;

}
