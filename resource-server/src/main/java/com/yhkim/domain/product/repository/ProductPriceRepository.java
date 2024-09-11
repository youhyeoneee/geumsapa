package com.yhkim.domain.product.repository;

import com.yhkim.domain.product.entity.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Integer> {
}
