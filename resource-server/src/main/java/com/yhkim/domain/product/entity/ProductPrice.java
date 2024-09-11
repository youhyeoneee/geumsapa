package com.yhkim.domain.product.entity;

import com.yhkim.domain.order.entity.OrderType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_prices")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Comment("상품")
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, updatable = false)
    private Product product;
    
    @Comment("거래 유형 [SELL, BUY]")
    @Column(nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private OrderType orderType;
    
    @Comment("센티그램(0.01g)당 가격")
    @Column(nullable = false, updatable = false)
    private Integer pricePerCentigramme;
    
    @CreatedDate
    @Column(nullable = false, updatable = false, columnDefinition = "timestamp")
    private LocalDateTime createdAt;
    
    // TODO: 나중에 @CreatedBy 적용
    @Column(nullable = false, updatable = false)
    private Integer createdBy;
}
