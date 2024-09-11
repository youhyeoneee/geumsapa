package com.yhkim.domain.order.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.yhkim.domain.order.entity.Order;
import com.yhkim.domain.order.entity.OrderType;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderDetailResponse {
    private Integer orderId;
    private String orderNumber;
    private OrderType orderType;
    private Integer orderUserId;
    private String product;
    private String status;
    private String deliveryAddress;
    private BigDecimal quantity;
    private Integer totalPrice;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deletedAt;
    
    public static OrderDetailResponse fromEntity(Order order) {
        return OrderDetailResponse.builder()
                .orderId(order.getId())
                .orderNumber(order.getOrderNumber())
                .orderType(order.getOrderType())
                .orderUserId(order.getOrderUserId())
                .product(order.getProduct().getName())
                .status(order.getStatus().getDescription())
                .deliveryAddress(order.getDeliveryAddress())
                .quantity(order.getQuantity())
                .totalPrice(order.getTotalPrice())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .deletedAt(order.getDeletedAt())
                .build();
    }
}
