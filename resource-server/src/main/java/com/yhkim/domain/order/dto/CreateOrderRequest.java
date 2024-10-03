package com.yhkim.domain.order.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.yhkim.domain.order.entity.Order;
import com.yhkim.domain.order.entity.OrderStatus;
import com.yhkim.domain.order.entity.OrderType;
import com.yhkim.domain.product.entity.Product;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateOrderRequest {
    
    
    @NotNull(message = "Invalid order type format.")
    private OrderType orderType;
    
    @NotNull(message = "Product Id is required.")
    @Min(value = 1, message = "Product Id must be 1 and greater.")
    private Integer productId;
    
    @NotBlank(message = "Delivery address is required.")
    private String deliveryAddress;
    
    @NotNull(message = "Quantity is required.")
    @DecimalMin(value = "0.01", message = "Quantity must be 0.01 or greater.")
    @Digits(integer = Integer.MAX_VALUE, fraction = 2,
            message = "Quantity must have at most 2 decimal places.")
    private BigDecimal quantity;
    
    public Order toEntity(Integer userId, Product product, Integer totalPrice) {
        return Order.builder()
                .orderType(orderType)
                .orderUserId(userId)
                .deliveryAddress(deliveryAddress)
                .quantity(quantity)
                .product(product)
                .status(OrderStatus.ORDER_COMPLETED)
                .totalPrice(totalPrice)
                .build();
    }
}
