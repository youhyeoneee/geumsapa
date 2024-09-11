package com.yhkim.domain.product.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductCode {
    GOLD_99_99("Gold-99.99"),
    GOLD_99_9("Gold-99.9");
    
    private final String code;
}
