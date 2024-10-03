package com.yhkim.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    
    // user
    INVALID_INPUT_FORMAT(HttpStatus.BAD_REQUEST, "E400000", "Invalid input format."),
    INVALID_USERNAME_FORMAT(HttpStatus.BAD_REQUEST, "E400001", "Invalid username format."),
    INVALID_PASSWORD_FORMAT(HttpStatus.BAD_REQUEST, "E400002", "Invalid password format."),
    INVALID_FULL_NAME_FORMAT(HttpStatus.BAD_REQUEST, "E400003", "Invalid full name format."),
    INVALID_PHONE_NUMBER_FORMAT(HttpStatus.BAD_REQUEST, "E400004", "Invalid phone number format."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "E400005", "Incorrect password."),
    MISSING_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "E400006", "Required request header 'Refresh-Token' is missing."),
    INVALID_TOKEN_TYPE(HttpStatus.BAD_REQUEST, "E400007", "Invalid token type. Only refresh tokens are accepted for this operation."),
    // order
    INVALID_QUANTITY_FORMAT(HttpStatus.BAD_REQUEST, "E400008", "Invalid quantity format."),
    INVALID_ORDER_TYPE_FORMAT(HttpStatus.BAD_REQUEST, "E400009", "Invalid order type format."),
    INVALID_ORDER_STATUS(HttpStatus.BAD_REQUEST, "E400010", "Invalid order status."),
    INVALID_ORDER_STATUS_SEQUENCE(HttpStatus.BAD_REQUEST, "E400011", "Invalid order status sequence."),
    INVALID_INVOICE_TYPE(HttpStatus.BAD_REQUEST, "E400012", "Invalid invoice type parameter. expected: ['buy', 'sell]"),
    
    
    // user
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "E401001", "Refresh token is invalid or does not exist."),
    ACCESS_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "E401002", "Access token has expired. Please use the refresh token to reissue a new access token."),
    REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "E401003", "Refresh token has expired. Please log in again to continue."),
    REFRESH_TOKEN_MISMATCH(HttpStatus.UNAUTHORIZED, "E401004", "The provided refresh token does not match the stored token."),
    
    ORDER_OWNERSHIP_MISMATCH(HttpStatus.FORBIDDEN, "E403001", "The order does not belong to the authenticated user."),
    
    // user
    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND, "E404001", "Username not found."),
    // product
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "E404002", "Product not found."),
    PRODUCT_PRICE_NOT_FOUND(HttpStatus.NOT_FOUND, "E404003", "Product price not found."),
    // order
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "E404004", "Order not found."),
    
    // user
    USERNAME_ALREADY_EXIST(HttpStatus.CONFLICT, "E409001", "Username already exists."),
    // order
    ORDER_CANCELLATION_NOT_ALLOWED(HttpStatus.CONFLICT, "E409002", "Order cancellation is not allowed. The order has already been received or shipped."),
    ORDER_ALREADY_CANCELLED(HttpStatus.CONFLICT, "E409003", "The order has already been cancelled.");
    
    private final HttpStatus httpStatus;
    private final String errorCode;
    private String message;
    
    public void setMessage(String message) {
        this.message = message;
    }
}
