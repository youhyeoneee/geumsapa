package com.yhkim.domain.order.entity;

import com.yhkim.domain.order.dto.OrderDetailResponse;
import com.yhkim.domain.product.entity.Product;
import com.yhkim.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Order extends BaseEntity {
    @Column(unique = true)
    private String orderNumber;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderType orderType;
    
    @Column(nullable = false)
    private Integer orderUserId;
    
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;
    
    @Column(nullable = false)
    private String deliveryAddress;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal quantity;
    
    @Column(nullable = false)
    private Integer totalPrice;
    
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    
    public OrderDetailResponse getOrderDetail() {
        return OrderDetailResponse.builder()
                .orderId(getId())
                .orderNumber(orderNumber)
                .orderType(orderType)
                .orderUserId(orderUserId)
                .product(product.getName())
                .status(status.getDescription())
                .deliveryAddress(deliveryAddress)
                .quantity(quantity)
                .totalPrice(totalPrice)
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())
                .deletedAt(getDeletedAt())
                .build();
    }
}
