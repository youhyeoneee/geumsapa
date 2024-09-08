package com.yhkim.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    
    INVALID_USERNAME_FORMAT(HttpStatus.BAD_REQUEST, "E400001", "Invalid username format."),
    INVALID_PASSWORD_FORMAT(HttpStatus.BAD_REQUEST, "E400002", "Invalid password format."),
    INVALID_FULL_NAME_FORMAT(HttpStatus.BAD_REQUEST, "E400003", "Invalid full name format."),
    INVALID_PHONE_NUMBER_FORMAT(HttpStatus.BAD_REQUEST, "E400004", "Invalid phone number format."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "E400005", "Incorrect password."),
    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND, "E404001", "Username not found."),
    USERNAME_ALREADY_EXIST(HttpStatus.CONFLICT, "E409001", "Username already exists.");
    
    private final HttpStatus httpStatus;
    private final String errorCode;
    private String message;
    
    public void setMessage(String message) {
        this.message = message;
    }
}
