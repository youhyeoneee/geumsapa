package com.yhkim.exception;


import com.yhkim.util.ApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
}
