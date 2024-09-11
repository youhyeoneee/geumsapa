package com.yhkim.domain.order.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum OrderType {
    BUY("BY"),
    SELL("SL");
    
    private final String code;
    
    
    @JsonCreator
    public static OrderType parsing(String inputValue) {
        return Stream.of(OrderType.values())
                .filter(orderType -> orderType.name().equals(inputValue.toUpperCase()))
                .findFirst()
                .orElse(null);
    }
}
