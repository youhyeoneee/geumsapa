package com.yhkim.domain.product.repository;

import com.yhkim.domain.order.entity.OrderType;
import com.yhkim.domain.product.entity.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Integer> {
    
    Optional<ProductPrice> findTopByOrderTypeOrderByCreatedAtDesc(OrderType orderType);
    
}
