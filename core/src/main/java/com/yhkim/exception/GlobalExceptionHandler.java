package com.yhkim.exception;


import com.yhkim.util.ApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.yhkim.util.ApiUtils.failed;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<ApiUtils.FailedResponse> handleCustomException(CustomException ex) {
        
        log.error("handleCustomException", ex);
        
        ErrorCode errorCode = ex.getErrorCode();
        
        return failed(errorCode);
    }
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiUtils.FailedResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        
        log.error("handleValidationExceptions", ex);
        
        for (FieldError error : ex.getFieldErrors()) {
            
            String field = error.getField();
            String errorMessage = error.getDefaultMessage();
            
            switch (field) {
                case "username" -> {
                    ErrorCode errorCode = ErrorCode.INVALID_USERNAME_FORMAT;
                    errorCode.setMessage(errorMessage);
                    return failed(errorCode);
                }
                case "password" -> {
                    ErrorCode errorCode = ErrorCode.INVALID_PASSWORD_FORMAT;
                    errorCode.setMessage(errorMessage);
                    return failed(errorCode);
                }
                case "fullName" -> {
                    ErrorCode errorCode = ErrorCode.INVALID_FULL_NAME_FORMAT;
                    errorCode.setMessage(errorMessage);
                    return failed(errorCode);
                }
                case "phoneNumber" -> {
                    ErrorCode errorCode = ErrorCode.INVALID_PHONE_NUMBER_FORMAT;
                    errorCode.setMessage(errorMessage);
                    return failed(errorCode);
                }
                case "quantity" -> {
                    ErrorCode errorCode = ErrorCode.INVALID_QUANTITY_FORMAT;
                    errorCode.setMessage(errorMessage);
                    return failed(errorCode);
                }
                case "orderType" -> {
                    ErrorCode errorCode = ErrorCode.INVALID_ORDER_TYPE_FORMAT;
                    errorCode.setMessage(errorMessage);
                    return failed(errorCode);
                }
                case "orderStatus" -> {
                    ErrorCode errorCode = ErrorCode.INVALID_ORDER_STATUS;
                    errorCode.setMessage(errorMessage);
                    return failed(errorCode);
                }
                default -> {
                    ErrorCode errorCode = ErrorCode.INVALID_INPUT_FORMAT;
                    errorCode.setMessage("Invalid " + field + " format");
                    return failed(errorCode);
                }
            }
        }
        
        // TODO: MethodArgumentNotValidException 일어나는 다른 경우 추가하기
        
        return null;
    }
    
    @ExceptionHandler(value = {MissingRequestHeaderException.class})
    public ResponseEntity<ApiUtils.FailedResponse> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {
        
        log.error("handleMissingRequestHeaderException", ex);
        
        String headerName = ex.getHeaderName();
        
        if (headerName.equals("Refresh-Token")) {
            return failed(ErrorCode.MISSING_REFRESH_TOKEN);
        }
        
        // TODO: MissingRequestHeaderException 일어나는 다른 경우 추가하기
        return null;
    }
}
