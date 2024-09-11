package com.yhkim.domain.order.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
