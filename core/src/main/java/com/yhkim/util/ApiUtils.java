package com.yhkim.util;

import com.yhkim.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiUtils {

	public static <T> ResponseEntity<ApiResult<T>> success(HttpStatus httpStatus, String message, T data) {
		ApiResult<T> apiResult = new ApiResult<>(true, httpStatus, message, data);
		return ResponseEntity.status(httpStatus).body(apiResult);
	}

	public static ResponseEntity<ApiError> error(ErrorCode errorCode) {
		ApiError apiError = new ApiError(false, errorCode.getHttpStatus(), errorCode, errorCode.getMessage());
		return ResponseEntity.status(errorCode.getHttpStatus()).body(apiError);
	}

	@Getter
	@AllArgsConstructor
	public static class ApiResult<T> {
		private final boolean success;
		private final HttpStatus httpStatus;
		private final String message;
		private final T data;
	}

	@Getter
	@AllArgsConstructor
	public static class ApiError {
		private final boolean success;
		private final HttpStatus httpStatus;
		private final ErrorCode errorCode;
		private final String message;
	}
}
